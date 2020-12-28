#ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

SELECT
	m1.name as 'фильм 1',
	s1.`time` as 'время начала',
	m1.duration as 'длительность',
	m2.name as 'фильм 2',
	s2.`time` as 'время начала',
	m2.duration as 'длительность'
from
	sessions s1
LEFT JOIN movies m1 on
	s1.movie_id = m1.id
INNER JOIN sessions s2 on
	s2.`time` BETWEEN s1.`time` AND s1.`time` + INTERVAL m1.duration MINUTE AND s1.id != s2.id
INNER JOIN movies m2 on
	m2.id = s2.movie_id;
	
	
#перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

SELECT
	m1.name as 'фильм 1',
	s1.`time` as 'время начала',
	m1.duration as 'длительность',
	s2.`time` as 'время начала второго фильма',
	TIMESTAMPDIFF(MINUTE,
	s1.`time` + INTERVAL m1.duration MINUTE,
	s2.`time`) as 'длительность перерыва'
from
	sessions s1
LEFT JOIN movies m1 on
	s1.movie_id = m1.id
LEFT JOIN sessions s2 on
	s2.id =(
	SELECT
		id
	from
		sessions s
	WHERE
		s.`time` > s1.`time`
	ORDER BY
		s.`time` ASC
	LIMIT 1)
where
	TIMESTAMPDIFF(MINUTE,
	s1.`time` + INTERVAL m1.duration MINUTE,
	s2.`time`) > 30
ORDER BY
	TIMESTAMPDIFF(MINUTE,
	s1.`time` + INTERVAL m1.duration MINUTE,
	s2.`time`) DESC

#список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

SELECT
	m.name as 'фильм',
	sum(total_visits.count) as 'общеe число посетителей',
	avg(avg_visits.avg) as 'среднее число зрителей за сеанс',
	sum(total_visits.sum) as 'сумма сборов'
from
	movies m
LEFT JOIN (
	SELECT
		movie_id,
		count(t.id) as 'count',
		sum(s.price) as 'sum'
	from
		tickets t
	LEFT join sessions s on
		s.id = t.session_id
	group by
		movie_id ) total_visits on
	total_visits.movie_id = m.id
LEFT JOIN (
	SELECT
		movie_id,
		count(t.id)/ count(distinct s.id) as 'avg'
	from
		sessions s
	LEFT join tickets t on
		s.id = t.session_id
	group by
		movie_id) avg_visits on
	avg_visits.movie_id = m.id
GROUP BY
	m.id WITH ROLLUP


#число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

select
	"с 9 до 15" as 'время',
	sum(s.price) as 'сборы',
	count(s.id) as 'число посетителей'
from
	tickets t
LEFT JOIN sessions s on
	s.id = t.session_id
where
	CAST(s.`time` AS time) >= '09:00:00'
	and CAST(s.`time` AS time) < '15:00:00'
UNION ALL 
select
	"с 15 до 18" as 'время',
	sum(s.price) as 'сборы',
	count(s.id) as 'число посетителей'
from
	tickets t
LEFT JOIN sessions s on
	s.id = t.session_id
where
	CAST(s.`time` AS time) >= '15:00:00'
	and CAST(s.`time` AS time) < '18:00:00'
	UNION ALL 
select
	"с 18 до 21" as 'время',
	sum(s.price) as 'сборы',
	count(s.id) as 'число посетителей'
from
	tickets t
LEFT JOIN sessions s on
	s.id = t.session_id
where
	CAST(s.`time` AS time) >= '18:00:00'
	and CAST(s.`time` AS time) < '21:00:00'
		UNION ALL 
select
	"с 21 до 24" as 'время',
	sum(s.price) as 'сборы',
	count(s.id) as 'число посетителей'
from
	tickets t
LEFT JOIN sessions s on
	s.id = t.session_id
where
	CAST(s.`time` AS time) >= '21:00:00'
	and CAST(s.`time` AS time) < '24:00:00'