create table course_schema.ext_lab2_genome_sequences (
	id serial not null,
	genome_id varchar not null, --идентификатор генома
	seq_length int not null, --длина последовательности (шингла)
	aa_sequence varchar not null --последовательность (шингл)
);

--функция, кот. рассчитывает показатель Жаккара на шинглах переданной длины
CREATE OR REPLACE FUNCTION course_schema.processgenseqdata(shinglelength integer)
 RETURNS SETOF numeric
 LANGUAGE sql
AS $function$
		with gen1_len as (select distinct aa_sequence --уникальные шинглы первого генома
							from ext_lab2_genome_sequences
						  where genome_id = 'Genome_1-1' and seq_length = shingleLength),
			 gen2_len as (select distinct aa_sequence --уникальные шинглы второго генома
							from ext_lab2_genome_sequences
							where genome_id = 'Genome_2-1' and seq_length = shingleLength),
			 intersection_len2 as (select count(*) as cnt from gen1_len g1 --пересечение уникальных шинглов
			 				inner join gen2_len g2 on g1.aa_sequence = g2.aa_sequence),
			 union_len2 as (select count(distinct inner_.*) as cnt from (select * from gen1_len g1 --объединение уникальных шинглов
									union all
									select * from gen2_len) as inner_)
		select
		trunc(inters.cnt::numeric / un.cnt::numeric, 2) as res --отношение мощности пересечения к мощности объединения
		from
		intersection_len2 inters
		left join union_len2 un on true
$function$
;

--получение итоговой таблицы
select 2 as k, processGenSeqData(2) as j
union
select 5, processGenSeqData(5)
union
select 9, processGenSeqData(9)

--итоговая таблица:
k|   j|
-+----+
2|1.00|
5|1.00|
9|0.63|
