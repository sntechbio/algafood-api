create table estado (
                        id bigint not null auto_increment,
                        nome varchar(80) not null,

                        primary key (id)
) engine=InnoDB default charset=utf8;

insert into estado (nome)
select distinct nome_estado from cidade;

# adiconando coluna para criar referencia do estado
alter table cidade add column estado_id bigint not null;

# referenciando coluna com valores id de outra coluna
update cidade c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);

# adicionar chave estrangeira para coluna específica
alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado (id);

# excluir coluna
alter table cidade drop column nome_estado;

# muda nome de coluna
alter table cidade change nome_cidade nome varchar(80) not null;