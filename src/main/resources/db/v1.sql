create table coupons(
    id varbinary(16) not null primary key,
    description varchar(50) not null,
    value double(4,2) not null
) Engine = InnoDB;

create table payments(
    order_id varbinary(16) not null primary key,
    coupon_id varbinary(16) null,
    total float(4,2) not null,
    constraint fk_coupon foreign key (coupon_id) references coupons(id)
) Engine = InnoDB;

create table items(
    id varbinary(16) not null primary key,
    item_id varbinary(16) not null,
    order_id varbinary(16) not null,
    description varchar(50) not null,
    value double(4,2) not null
) Engine = InnoDB;