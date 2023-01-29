create table course_schema.ext_lab2_genome_sequences (
	id serial not null,
	genome_id varchar not null, --идентификатор генома
	seq_length int not null, --длина последовательности (шингла)
	aa_sequence varchar not null --последовательность (шингл)
);
