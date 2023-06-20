create table if not exists Director(
                         id serial primary key,
                         nameDirector VARCHAR,
                         sonameDirector VARCHAR,
                         ageDirector INT,
                         dateGetPositionDirector date
);

create table if not exists Store(
                      idStore serial primary key,
                      nameStore varchar,
                      cityStore varchar,
                      streetStore varchar,
                      idDirectorStore int REFERENCES Director(id),
                      dateOpenStore date
);

create table if not exists Author(
                       idAuthor serial primary key,
                       nameAuthor varchar,
                       sonameAuthor varchar,
                       fanameAuthor varchar,
                       dateAddToAuthor date
);

create table if not exists Book(
                     idBook serial primary key,
                     nameBook varchar,
                     describeBook varchar,
                     idAuthorBook int REFERENCES Author(idAuthor),
                     idStoreBook int REFERENCES Store(idStore),
                     dateStartSaleBook date
);