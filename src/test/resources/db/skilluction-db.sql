
INSERT INTO `courses` (`course_id`, `course_description`, `course_duration`, `course_name`, `instructor_id`) VALUES
(1, 'Master Spring Service', '2 Hours', 'Spring Service', 1),
(2, 'Introduction to JPA', '4 Hours', 'Spring Data JPA', 2);


INSERT INTO `enrolled_in` (`course_id`, `student_id`) VALUES
(1, 1),
(2, 2);


INSERT INTO `instructors` (`instructor_id`, `first_name`, `last_name`, `summary`, `user_id`) VALUES
(1, 'instructor1FN', 'instructor1LN', 'Experienced Instructor', 3),
(2, 'instructor2FN', 'instructor2LN', 'Senior Instructor', 4);


INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'Admin'),
(2, 'Instructor'),
(3, 'Student');


INSERT INTO `students` (`student_id`, `first_name`, `last_name`, `level`, `user_id`) VALUES
(1, 'std1FN', 'std1LN', 'beginner', 5),
(2, 'std2FN', 'std2LN', 'Master degree', 6);


INSERT INTO `users` (`user_id`, `email`, `password`) VALUES
(1, 'user1@gmail.com', 'pass1'),
(2, 'user2@gmail.com', 'pass2'),
(3, 'instructorUser1@gmail.com', 'pass1'),
(4, 'instructorUser2@gmail.com', 'pass2'),
(5, 'stdUser1@gmail.com', 'pass1'),
(6, 'stdUser2@gmail.com', 'pass2');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 2),
(4, 2),
(5, 3),
(6, 3);
