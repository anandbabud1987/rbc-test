DROP TABLE IF EXISTS data_set;

create table data_set(id INT AUTO_INCREMENT  PRIMARY KEY,
quarter VARCHAR(250),
            stock VARCHAR(250),
            date VARCHAR(250),
            open VARCHAR(250),
            high VARCHAR(250),
            low VARCHAR(250),
            close VARCHAR(250),
            volume VARCHAR(250),
            percent_change_price VARCHAR(250),
            percent_change_volume_over_last_wk VARCHAR(250),
            previous_weeks_volume VARCHAR(250),
            next_weeks_open,next_weeks_close VARCHAR(250),
            percent_change_next_weeks_price VARCHAR(250),
            days_to_next_dividend VARCHAR(250),
            percent_return_next_dividend VARCHAR(250))
