insert into genres(name) 
values 	('액션'), ('모험'), ('애니메이션'),('코미디'), ('범죄'),
 		('다큐멘터리'), ('드라마'), ('가족'), ('판타지'), ('역사'), 
 		('SF') , ('공포'), ('로맨스');
 		
 INSERT INTO cinemas (id, call_name, type, capacity) VALUES (4, '상무지구', '4d', 45),(5,'충장점', '2d',32),(6,'첨단점','4d',65),(7,'운암점','2d',55),(8,'경운점','2d',23);


insert into movies(id,title, released_at, running_time, genre_id, age_cut)
	values
	('d560532a-7b62-4e0b-804e-1fbcf8a4bf1c', '도그맨', '2024-01-10', 115, 7, 15),
	('6a89c452-ad5f-495e-bf12-22ec352f364d', '라라랜드', '2016-12-07', 127, 7, 12),
	('3dcce48e-c918-4ffd-9b67-de1172ce4eab', '인투 더 월드', '2024-01-10', 91, 3, null),
	('4cb56f21-89f0-463f-865d-112fdae20821', '스즈메의 문단속', '2023-03-08', 122, 3, 12),
	('e58acc06-09b2-4a6e-bc63-d481b84e4cf9', '노량-죽음의 바다', '2023-12-20', 153, 1, 12)	;
	
 		