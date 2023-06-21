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
SELECT 7777,
       'Test',
       'Test',
       54,
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT id FROM director WHERE id = 7777
    );

insert into store(idstore, namestore, citystore, streetstore, iddirectorstore, dateopenstore)
SELECT 7777,
       'Test',
       'Test',
       'Test',
       9999,
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT idstore FROM store WHERE idstore = 7777
    );

insert into store(idstore, namestore, citystore, streetstore, iddirectorstore, dateopenstore)
SELECT 8888,
       'Test',
       'Test',
       'Test',
       9999,
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT idstore FROM store WHERE idstore = 8888
    );
insert into author(idauthor, nameauthor, sonameauthor, fanameauthor, dateaddtoauthor)
SELECT 7777,
       'Test',
       'Test',
       'Test',
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT idauthor FROM author WHERE idauthor = 7777
    );

insert into author(idauthor, nameauthor, sonameauthor, fanameauthor, dateaddtoauthor)
SELECT 8888,
       'Test',
       'Test',
       'Test',
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT idauthor FROM author WHERE idauthor = 8888
    );

insert into book(idbook, namebook, describebook, idauthorbook, idstorebook, datestartsalebook)
SELECT 7777,
       'Test',
       'Test',
       7777,
       7777,
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT idbook FROM book WHERE idbook = 7777
    );

insert into book(idbook, namebook, describebook, idauthorbook, idstorebook, datestartsalebook)
SELECT 8888,
       'Test',
       'Test',
       7777,
       7777,
       '2023-06-20' WHERE
    NOT EXISTS (
        SELECT idbook FROM book WHERE idbook = 8888
    );