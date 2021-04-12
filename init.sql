create table user_tb (
    user_id serial primary key,
    username varchar(30) not null unique,
    passwd varchar(20) not null
);

create table truck_tb (
    truck_id integer primary key,
    status integer not null
);

create table tracking_ship_tb (
    tracking_id serial primary key,
    ship_id integer not null,
    truck_id integer references truck_tb(truck_id) default null
);

create table user_tracking_tb (
    id serial,
    user_id integer references user_tb(user_id),
    tracking_id integer references tracking_ship_tb(tracking_id)
);