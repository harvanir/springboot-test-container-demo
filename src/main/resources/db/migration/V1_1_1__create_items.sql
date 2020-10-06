create table items
(
    id         bigint(20)   not null auto_increment,
    name       varchar(200) not null,
    quantity   int(11)      not null,
    price      double,
    created_at datetime(3),
    updated_at datetime(3),
    version    bigint(20)   not null default 0,
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;