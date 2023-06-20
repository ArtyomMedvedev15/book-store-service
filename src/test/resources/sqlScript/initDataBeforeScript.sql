insert into director(id, namedirector, sonamedirector, agedirector, dategetpositiondirector)
SELECT 9999,
       'Test',
       'Test',
       54,
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT id FROM director WHERE id = 9999
    );

insert into director(id, namedirector, sonamedirector, agedirector, dategetpositiondirector)
values (7777, 'TestDelete', 'Test', 54, '2023-06-20');

insert into store(idstore, namestore, citystore, streetstore, iddirectorstore, dateopenstore)
values (7777,'Test','Test','Test',9999,'2023-06-20');

insert into store(idstore, namestore, citystore, streetstore, iddirectorstore, dateopenstore)
values (8888,'Test','Test','Test',9999,'2023-06-20');