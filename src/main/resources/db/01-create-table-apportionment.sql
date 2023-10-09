create table apportionment
(
    id                   bigint unsigned auto_increment
        primary key,
    id_condominium       bigint unsigned not null,
    value                double          null,
    number_of_apartments int             null,
    value_for_apartments double          null,
    id_expense           bigint unsigned not null,
    constraint apportionment_expenses_idExpense_fk
        foreign key (id_expense) references expenses (idExpense)
            on update cascade on delete cascade,
    constraint apportionment_ibfk_1
        foreign key (id_condominium) references condominium (idCondominium)
            on update cascade on delete cascade
);

create index idCondominium
    on apportionment (id_condominium);