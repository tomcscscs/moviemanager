insert into cinemas(call_name, capacity, type) 
	values
	('이순신', 360, 'IMAX'),
	('세종대왕', 270, 'SCREENX'),
	('안중근', 120, '4D'),
	('윤동주', 100, '일반');

insert into genres(name) 
values 	('액션'), ('모험'), ('애니메이션'),('코미디'), ('범죄'),
 		('다큐멘터리'), ('드라마'), ('가족'), ('판타지'), ('역사'), 
 		('SF') , ('공포'), ('로맨스');


insert into movies(id,title, released_at, running_time, genre_id, age_cut)
	values
	('d560532a-7b62-4e0b-804e-1fbcf8a4bf1c', '도그맨', '2024-01-10', 115, 7, 15),
	('6a89c452-ad5f-495e-bf12-22ec352f364d', '라라랜드', '2016-12-07', 127, 7, 12),
	('3dcce48e-c918-4ffd-9b67-de1172ce4eab', '인투 더 월드', '2024-01-10', 91, 3, null),
	('4cb56f21-89f0-463f-865d-112fdae20821', '스즈메의 문단속', '2023-03-08', 122, 3, 12),
	('e58acc06-09b2-4a6e-bc63-d481b84e4cf9', '노량-죽음의 바다', '2023-12-20', 153, 1, 12)	;
	

insert into schedules(cinema_id, movie_id, show_date, show_time, closed_time, reserved_cnt)
	values
	(1, '6a89c452-ad5f-495e-bf12-22ec352f364d', '2024-01-30', '2024-01-30 13:10', '2024-01-30 15:17',0),
	(1, '6a89c452-ad5f-495e-bf12-22ec352f364d', '2024-01-31', '2024-01-31 17:00', '2024-01-31 19:07',0);  