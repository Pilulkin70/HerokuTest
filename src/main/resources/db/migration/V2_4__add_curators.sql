insert into "curator" (id, dateofbirth, experience, name, universitygroup_id)
values ('210dabb0-ece5-11ec-8ea0-0242ac120002','1997-12-19',20.2,'NewCurator-001','4eefcf48-ece2-11ec-8ea0-0242ac120002');

update universitygroup set curator_id = '210dabb0-ece5-11ec-8ea0-0242ac120002'
where universitygroup.id='4eefcf48-ece2-11ec-8ea0-0242ac120002';

insert into "curator" (id, dateofbirth, experience, name, universitygroup_id)
values ('5e91af36-ece5-11ec-8ea0-0242ac120002','2000-08-10',13.1,'NewCurator-002','60b116ec-ece2-11ec-8ea0-0242ac120002');

update universitygroup set curator_id = '5e91af36-ece5-11ec-8ea0-0242ac120002'
where universitygroup.id='60b116ec-ece2-11ec-8ea0-0242ac120002';