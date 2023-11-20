create table total_condominium
(
    id                   bigint unsigned auto_increment
        primary key,
    id_condominium       bigint unsigned not null,
    value                double          null,
    id_apartment bigint  unsigned not null,
    equal_apportionment double             not null,
    proportional_apportionment double             not null,
    condominium_total double             not null,
    funds double             not null,
    total double             not null

);

create index idCondominium
    on total_condominium (id_condominium);