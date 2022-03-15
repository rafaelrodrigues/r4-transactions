INSERT INTO ACCOUNT(document_number) VALUES('12345678900');

INSERT INTO TRANSACTION(account_id, operation_type_id, amount, event_date) VALUES(1, 1, '-50.0', NOW());
INSERT INTO TRANSACTION(account_id, operation_type_id, amount, event_date) VALUES(1, 1, '-23.5', NOW());
INSERT INTO TRANSACTION(account_id, operation_type_id, amount, event_date) VALUES(1, 1, '-18.7', NOW());
INSERT INTO TRANSACTION(account_id, operation_type_id, amount, event_date) VALUES(1, 4, '60.0', NOW());
