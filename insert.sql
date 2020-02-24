insert into dept values(1,'Grocery');
insert into dept values(2,'Household');
insert into dept values(3,'Stationery');
insert into dept values(4,'Kitchen');

insert into items values(1,11,'Apple',137,149);
insert into items values(1,12,'Cookies',27,30);
insert into items values(2,21,'Washing Powder',47,53);
insert into items values(2,22,'Body Soap',22,25);
insert into items values(3,31,'Pens',8,10);
insert into items values(3,32,'Notebooks',39,45);
insert into items values(4,41,'Cooker', 260,300);
insert into items values(4,42,'Non Stick Pan',450,500);


insert into supply values(1001,date '2019-04-02',11,50,date '2019-04-02',NULL);
insert into supply values(1002,date '2019-04-02',22,30,date '2019-04-02',date '2019-10-02');
insert into supply values(1003,date '2019-04-02',31,60,date '2019-04-02',date '2019-10-01');
insert into supply values(1004,date '2019-04-02',32,50,date '2019-04-02',NULL);
insert into supply values(1005,date '2019-04-02',41,20,date '2019-04-02',NULL);
insert into supply values(2001,date '2019-04-05',11,60,date '2019-04-05',NULL);
insert into supply values(2002,date '2019-04-05',12,40,date '2019-04-05',date '2019-05-15');
insert into supply values(2003,date '2019-04-05',21,30,date '2019-04-05',date '2019-10-05');
insert into supply values(2004,date '2019-04-05',42,15,date '2019-04-05',NULL);
insert into supply values(2005,date '2019-04-05',32,20,date '2019-04-05',NULL);

insert into sales values(001,date '2019-04-03',11,0);
insert into sales values(001,date '2019-04-03',22,10);
insert into sales values(001,date '2019-04-03',31,15);
insert into sales values(001,date '2019-04-03',32,10);
insert into sales values(001,date '2019-04-03',41,2);
insert into sales values(002,date '2019-04-06',11,20);
insert into sales values(002,date '2019-04-06',12,15);
insert into sales values(002,date '2019-04-06',21,10);

insert into stock values(11,50);
insert into stock values(12,25);
insert into stock values(21,20);
insert into stock values(22,15);
insert into stock values(31,45);
insert into stock values(32,40);
insert into stock values(41,18);
insert into stock values(42,15);



///////////////
create or replace trigger sup_stck
after update or insert on supply
for each row
begin
if updating then
update stock set qty=qty+:new.sup_qty where item_id=:new.item_id;
end if;
if inserting then
insert into stock values(:new.item_id,:new.sup_qty);
end if;
end;
/
//////////

create or replace trigger sup_stck
after insert on supply
for each row
begin
if inserting then

update stock set qty=qty+:new.sup_qty where item_id=:new.item_id;

end if;
end;
/



create or replace trigger sale_stck
before insert on sales
for each row
begin
if inserting then
update stock set qty=qty-:new.sale_qty where item_id=:new.item_id;
end if;
end;
/
 