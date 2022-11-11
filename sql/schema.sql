create table public.user (
     id bigserial primary key,
     email varchar(100) unique not null,
     password char(40) not null,
     name varchar(100) not null
);

create table category (
     id bigserial primary key,
     name varchar not null,
     authorId bigint,
     income boolean not null,

     unique (authorId, name),
     foreign key (authorId) references public.user(id) on delete cascade
);

create table moneyOperation (
    id bigserial primary key,
    authorId bigint not null,
    sum float not null,
    date date not null default now(),
    categoryId bigint,
    description text,
    income boolean not null,

    foreign key (authorId) references public.user(id) on delete cascade,
    foreign key (categoryId) references category(id) on delete set null
);

create table cashSaving (
    id bigserial primary key,
    name varchar(100) not null,
    authorId bigint not null,
    sum float not null,

    foreign key (authorId) references public.user(id) on delete cascade
);
