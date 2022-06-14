package com.heroku.hw25.service;

import com.heroku.hw25.dao.CuratorDao;
import com.heroku.hw25.model.Curator;
import com.heroku.hw25.model.UniversityGroup;

import java.time.LocalDate;
import java.util.Date;

import static com.heroku.hw25.Main.RANDOM;

public class CuratorService {
    public void addCurator(UniversityGroup universityGroup) {
        RandomDate randomDate = new RandomDate(LocalDate.of(1972, 1, 1),
                LocalDate.of(2000, 12, 31));
        final Curator curator = new Curator("Curator-" + RANDOM.nextInt(10, 1000),
                randomDate.nextDate(), RANDOM.nextDouble(1, 20));
        universityGroup.setCurator(curator);
        curator.setUniversityGroup(universityGroup);
    }

    public static void printCuratorBornBeforeAndWithExperienceGreatThan(Date maxDataBorn, Double minExperience) {
        final CuratorDao curatorDao = new CuratorDao();
        curatorDao.getCuratorBornBeforeAndWithExperienceGreatThan(maxDataBorn, minExperience).
                forEach(System.out::println);
    }
}
