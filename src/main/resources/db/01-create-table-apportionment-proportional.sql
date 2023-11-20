create table apportionment_proportional
(
    id                   bigint unsigned auto_increment
        primary key,
    id_condominium       bigint unsigned not null,
    value                double          null,
    id_apartment bigint  unsigned not null,
    initial_reading double             not null,
    final_reading double             not null,
    consumption double             not null,
    id_expense           bigint unsigned not null,
    constraint apportionment_proportional_expenses_idExpense_fk
        foreign key (id_expense) references expenses (idExpense)
            on update cascade on delete cascade,
    constraint apportionment_proportional_ibfk_1
        foreign key (id_condominium) references condominium (idCondominium)
            on update cascade on delete cascade,
    constraint apportionment_proportional_id_apartment
        foreign key (id_apartment) references apartments (idApartment)
             on update cascade on delete cascade
);

create index idCondominium
    on apportionment_proportional (id_condominium);