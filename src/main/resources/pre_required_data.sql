SELECT  * FROM customer;
SELECT * FROM user_role;
SELECT * FROM login;
SELECT * FROM city;
SELECT * FROM service_category;
SELECT * FROM service_sub_category;
SELECT * FROM service;

DESC user_role;
DESC service;

DROP DATABASE knock_knock;
DELETE FROM customer;
DELETE FROM user_role;
DELETE FROM login;
DELETE FROM service_category;


########## run below queries ##############

USE knock_knock;

INSERT INTO user_role (user_role_name) VALUES ("admin"), ("customer"), ("professional");

INSERT INTO city (city_country, city_name, city_state) VALUES ("india", "bangalore", "karnataka"),
                                                              ("india", "mehsana", "gujarat");

INSERT INTO service_category (service_category_id, service_category_name) VALUES ( "1", "photography"),
                                                            ( "2", "others");

INSERT INTO service_sub_category (service_sub_category_id, service_sub_category_desc, service_sub_category_name, service_category_service_category_id)
    VALUES ("1", "Hire Best Portrait Photographer", "birthday party", "1"),
           ("2", "Hire Best Candid Photographer", "anniversary function", "1"),
           ("3", "Hire Best Event Photographer", "corporate event", "1"),
           ("4", "Hire Best Photographer for Cocktail/Other personal party", "other party", "1"),
           ("5", "under development", "coming soon...", "2");

INSERT INTO service (service_commission, service_desc, service_name, service_sub_category_service_sub_category_id)
VALUES ("30", "[3hrs]Unlimited Clicks + Soft Copies Only", "Photography", "1"),
       ("25", "[3hrs]Unlimited Clicks + Soft Copies + 20 Hard Copies", "Photography with 20 Hard Copies", "1"),
       ("20", "[3hrs]Unlimited Clicks + Soft Copies + 20 Hard Copies + Photo Editing", "Photography with 20 Studio Hard Copies", "1"),
       ("15", "[3hrs]Unlimited Clicks + Video + Soft Copies Only", "Photography & Videography", "1"),
       ("10", "[3hrs]Unlimited Clicks + Video + Soft Copies + 20 Hard Copies", "Photography & Videography & 20 Hard Copies ", "1"),
       ("5", "[3hrs]Videography + Soft Copy", "Videography", "1"),
       ("30", "[3hrs]Unlimited Clicks + Soft Copies Only", "Photography", "2"),
       ("25", "[3hrs]Unlimited Clicks + Soft Copies + 20 Hard Copies", "Photography with 20 Hard Copies", "2"),
       ("20", "[3hrs]Unlimited Clicks + Soft Copies + 20 Hard Copies + Photo Editing", "Photography with 20 Studio Hard Copies", "2"),
       ("15", "[3hrs]Unlimited Clicks + Video + Soft Copies Only", "Photography & Videography", "2"),
       ("10", "[3hrs]Unlimited Clicks + Video + Soft Copies + 20 Hard Copies", "Photography & Videography & 20 Hard Copies ", "2"),
       ("5", "[3hrs]Videography + Soft Copy", "Videography", "2"),
       ("15", "[3hrs]Unlimited Clicks + Video + Soft Copies Only", "Photography & Videography", "3"),
       ("10", "[3hrs]Unlimited Clicks + Video + Soft Copies + 20 Hard Copies", "Photography & Videography & 20 Hard Copies ", "3"),
       ("5", "[3hrs]Videography + Soft Copy", "Videography", "3"),
       ("30", "[3hrs]Unlimited Clicks + Soft Copies Only", "Photography", "4"),
       ("25", "[3hrs]Unlimited Clicks + Soft Copies + 20 Hard Copies", "Photography with 20 Hard Copies", "4"),
       ("20", "[3hrs]Unlimited Clicks + Soft Copies + 20 Hard Copies + Photo Editing", "Photography with 20 Studio Hard Copies", "4"),
       ("15", "[3hrs]Unlimited Clicks + Video + Soft Copies Only", "Photography & Videography", "4"),
       ("10", "[3hrs]Unlimited Clicks + Video + Soft Copies + 20 Hard Copies", "Photography & Videography & 20 Hard Copies ", "4");

INSERT INTO login (login_id, last_login_date, mobile_no, password, register_date,status, user_role_id) VALUES("1", now(), "7600216322","Abcd@1234", now(), "a", "3");
INSERT INTO login (login_id, last_login_date, mobile_no, password, register_date,status, user_role_id) VALUES("2", now(), "7600216323","Abcd@1234", now(), "a", "3");
INSERT INTO login (login_id, last_login_date, mobile_no, password, register_date,status, user_role_id) VALUES("3", now(), "7600216324","Abcd@1234", now(), "a", "3");

INSERT INTO professional (professional_id, professional_birth_date, professional_email, professional_experience, professionalgstno, professional_gender, professional_name, login_login_id, serving_city_city_id) VALUES("1", now(), "a1@gmail.com", 10, "123", "Male", "Dhruv", "1",  "1");
INSERT INTO professional (professional_id, professional_birth_date, professional_email, professional_experience, professionalgstno, professional_gender, professional_name, login_login_id, serving_city_city_id) VALUES("2", now(), "a2@gmail.com", 10, "123", "Femal", "Anmol", "2", "1");
INSERT INTO professional (professional_id, professional_birth_date, professional_email, professional_experience, professionalgstno, professional_gender, professional_name, login_login_id, serving_city_city_id) VALUES("3", now(), "a3@gmail.com", 10, "123", "Male", "Henil", "3", "1");

INSERT INTO professional_service (service_estimated_time, service_cost, service_extra_cost, service_extra_cost_desc, professional_professional_id, service_service_id) VALUES (now(), "101",	"10", "For an Extra Hr.", "1", "1");
INSERT INTO professional_service (service_estimated_time, service_cost, service_extra_cost, service_extra_cost_desc, professional_professional_id, service_service_id) VALUES (now(), "202",	"20", "For an Extra Hr.", "1", "3");
INSERT INTO professional_service (service_estimated_time, service_cost, service_extra_cost, service_extra_cost_desc, professional_professional_id, service_service_id) VALUES (now(), "404", "40", "For an Extra Hr.", "3", "2");
INSERT INTO professional_service (service_estimated_time, service_cost, service_extra_cost, service_extra_cost_desc, professional_professional_id, service_service_id) VALUES (now(), "303",	"30", "For an Extra Hr.", "2", "1");
