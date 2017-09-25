create trigger LogGradeChange
	after update on ENROLL
	when old.Grade<>new.Grade
begin
	insert into GRADE_LOG(UserName, DateChanged, EId, OldGrade, NewGrade)
	values (current_user, current_date, new.EId, old.Grade, newrow.Grade);
end;
