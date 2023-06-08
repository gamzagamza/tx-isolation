drop table if exists card;

create table card (
      card_id bigint not null auto_increment,
      card_user varchar(100),
      primary key (card_id)
);
