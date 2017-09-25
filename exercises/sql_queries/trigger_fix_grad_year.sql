create trigger FixBadGradYear
	after insert on STUDENT
	for each row
	when new.GradYear > extract(YEAR, current_date) + 4
begin
	update STUDENT set GradYear = null;
end