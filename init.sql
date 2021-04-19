create table user_tb (
                         user_id serial primary key,
                         username varchar(30) not null unique,
                         passwd varchar(20) not null,
                         email varchar(30)
);

create table tracking_ship_tb (
                                  tracking_id serial primary key,
                                  ship_id integer not null,
                                  truck_id integer default null,
                                  status varchar(30) not null,
                                  dest_x integer,
                                  dest_y integer,
                                  comment varchar(200),
                                  distance double precision,
                                      whID integer
);

create table user_tracking_tb (
                                  id serial,
                                  user_id integer references user_tb(user_id),
                                  tracking_id integer references tracking_ship_tb(tracking_id)
);

create table truck_tb (
                          truck_id integer primary key,
                          status varchar(30) not null,
                          pos_x integer,
                          pos_y integer
);

create table product_tb (
                            id serial primary key,
                            pro_id integer,
                            ship_id integer not null,
                            name varchar(30) not null,
                            description varchar(100) not null,
                            cnt integer not null
);
