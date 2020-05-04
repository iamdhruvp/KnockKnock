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

