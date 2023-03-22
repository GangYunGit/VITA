package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.entity.*;
import com.ssafy.vitawearable.repo.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WearableImpl implements Wearable {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;
    private final TotalScoreRepo totalScoreRepo;
    private final UserHistoryRepo userHistoryRepo;
//    private final FriendRepo friendRepo;
//    private final UserRepo UserRepo;
    private final ModelMapper mapper = new ModelMapper();


    // 걸음수 달별
    @Override
    public List<StepMonthlyDto> stepMonthly(String userId) {
        List<MonthlyWearable> monthlyEnergy = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyEnergy.stream()
                .map(monthly -> mapper.map(monthly, StepMonthlyDto.class)).collect(Collectors.toList());
    }

    // 걸음수 주별
    @Override
    public List<StepWeeklyDto> stepWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, StepWeeklyDto.class)).collect(Collectors.toList());
    }

    // 걸음수 일별
    @Override
    public List<StepDailyDto> stepDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, StepDailyDto.class)).collect(Collectors.toList());
    }

    // 걸음수 이전과 비교
    public StepPastAndNowDto stepPastAndNow(String userId) {

        StepPastAndNowDto stepPastAndNowDto = new StepPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        stepPastAndNowDto.setWeekNowWearableStep(
                weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(lastExportWeek))
                .findFirst().get().getWeeklyWearableStep());

        // 이전 주 값 넣어주기
        stepPastAndNowDto.setWeekPastWearableStep(
                weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek))
                .findFirst().get().getWeeklyWearableStep());

        // 현재 달 값 넣어주기
        stepPastAndNowDto.setMonthNowWearableStep(
                monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                             w.getDate().getMonth() == LastExportTime.getMonth())
                .findFirst().get().getMonthlyWearableStep());

        // 이전 달 값 넣어주기
        stepPastAndNowDto.setMonthPastWearableStep(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableStep());

        // 이번 년도 값 평균 구해서 넣어주기
        stepPastAndNowDto.setYearNowWearableStep(
                (int)monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                .map(MonthlyWearable::getMonthlyWearableStep)
                .mapToInt(num -> num)
                .summaryStatistics()
                .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        stepPastAndNowDto.setYearPastWearableStep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableStep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return stepPastAndNowDto;
    }



    // 활동량 달별
    @Override
    public List<EnergyMonthlyDto> energyMonthly(String userId) {
        List<MonthlyWearable> monthlyEnergy = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyEnergy.stream()
                .map(monthly -> mapper.map(monthly, EnergyMonthlyDto.class)).collect(Collectors.toList());
    }

    // 활동량 주별
    @Override
    public List<EnergyWeeklyDto> energyWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, EnergyWeeklyDto.class)).collect(Collectors.toList());
    }

    // 활동량 일별
    @Override
    public List<EnergyDailyDto> energyDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, EnergyDailyDto.class)).collect(Collectors.toList());
    }

    // 활동에너지 이전과 비교
    public EnergyPastAndNowDto energyPastAndNow(String userId) {

        EnergyPastAndNowDto energyPastAndNowDto = new EnergyPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        energyPastAndNowDto.setWeekNowWearableEnergy(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(lastExportWeek))
                        .findFirst().get().getWeeklyWearableEnergy());

        // 이전 주 값 넣어주기
        energyPastAndNowDto.setWeekPastWearableEnergy(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(pastExportWeek))
                        .findFirst().get().getWeeklyWearableEnergy());

        // 현재 달 값 넣어주기
        energyPastAndNowDto.setMonthNowWearableEnergy(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                                w.getDate().getMonth() == LastExportTime.getMonth())
                        .findFirst().get().getMonthlyWearableEnergy());

        // 이전 달 값 넣어주기
        energyPastAndNowDto.setMonthPastWearableEnergy(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableEnergy());

        // 이번 년도 값 평균 구해서 넣어주기
        energyPastAndNowDto.setYearNowWearableEnergy(
                (long)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableEnergy)
                        .mapToLong(num -> num)
                        .summaryStatistics()
                        .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        energyPastAndNowDto.setYearPastWearableEnergy(
                (long)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableEnergy)
                        .mapToLong(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return energyPastAndNowDto;
    }

    // 심박수 달별
    @Override
    public List<RhrMonthlyDto> rhrMonthly(String userId) {
        List<MonthlyWearable> monthlyRhr = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyRhr.stream()
                .map(monthly -> mapper.map(monthly, RhrMonthlyDto.class)).collect(Collectors.toList());
    }

    // 심박수 주별
    @Override
    public List<RhrWeeklyDto> rhrWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, RhrWeeklyDto.class)).collect(Collectors.toList());

    }

    // 심박수 일별
    @Override
    public List<RhrDailyDto> rhrDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, RhrDailyDto.class)).collect(Collectors.toList());

    }

    // 수면 달별
    @Override
    public List<SleepMonthlyDto> sleepMonthly(String userId) {
        List<MonthlyWearable> monthlySleep = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlySleep.stream()
                .map(monthly -> mapper.map(monthly, SleepMonthlyDto.class)).collect(Collectors.toList());

    }

    // 수면 주별
    @Override
    public List<SleepWeeklyDto> sleepWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, SleepWeeklyDto.class)).collect(Collectors.toList());

    }

    // 수면 일별
    @Override
    public List<SleepDailyDto> sleepDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, SleepDailyDto.class)).collect(Collectors.toList());

    }

    // 스트레스 달별
    @Override
    public List<StressMonthlyDto> stressMonthly(String userId) {
        List<MonthlyWearable> monthlyStress = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyStress.stream()
                .map(monthly -> mapper.map(monthly, StressMonthlyDto.class)).collect(Collectors.toList());

    }

    // 스트레스 주별
    @Override
    public List<StressWeeklyDto> stressWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, StressWeeklyDto.class)).collect(Collectors.toList());

    }

    // 스트레스 일별
    @Override
    public List<StressDailyDto> stressDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, StressDailyDto.class)).collect(Collectors.toList());

    }

    // 무게 달별
    @Override
    public List<WeightMonthlyDto> weightMonthly(String userId) {
        List<MonthlyWearable> monthlyWeight = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyWeight.stream()
                .map(monthly -> mapper.map(monthly, WeightMonthlyDto.class)).collect(Collectors.toList());

    }

    // 무게 주별
    @Override
    public List<WeightWeeklyDto> weightWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, WeightWeeklyDto.class)).collect(Collectors.toList());

    }

    // 무게 일별
    @Override
    public List<WeightDailyDto> weightDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, WeightDailyDto.class)).collect(Collectors.toList());

    }

//    public WeightPastNowDto weightPastNow(String userId) {
//        WeightPastNowDto weightPastNowDto = new WeightPastNowDto();
//        weightPastNowDto = weeklyWearableRepo.findByUser_UserId(userId).get(0);
//
//    }

    // 모든 총합 점수 반환
    public List<TotalScoreDto> totalScore(String userId) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<TotalScore> totalScoreList = totalScoreRepo.findByUser_UserId(userId);
        List<TotalScoreDto> totalScoreDtoList = totalScoreList.stream()
                .map(totalScore -> mapper.map(totalScore, TotalScoreDto.class))
                .collect(Collectors.toList());
        // totalScore 구하기
        for (TotalScoreDto t:totalScoreDtoList) {
            int totalScore = t.getTotalScoreEnergy() + t.getTotalScoreWeight() +
                             t.getTotalScoreSleep() + t.getTotalScoreRhr() +
                             t.getTotalScoreStep() + t.getTotalScoreStress();
            t.setTotalScore(totalScore/6);
        }
        return totalScoreDtoList;
    }

    // 연도별 데일리 종합 점수 반환
    public List<DailyTotalScore> yearTotalScore(String userId, int year) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .filter(daily -> daily.getDate().getYear() == year)
                .map(daily -> mapper.map(daily, DailyTotalScore.class))
                .collect(Collectors.toList());
    }


    // 해당 유저 평균값 구하기
    @Override
    public UserAverageDto userAverage(String userId) {
        UserAverage userAverage = userAverageRepo.findByUser_UserId(userId).get(0);
        return mapper.map(userAverage, UserAverageDto.class);
    }

    // api 종합점수
    @Override
    public ApiAverageDto apiTotalAverage() {
        List<ApiAverage> apiAverageList = apiAverageRepo.findAll();
        ApiAverageDto apiAverageDto = new ApiAverageDto();
        apiAverageDto.setApiAverageStep((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageStep).average().getAsDouble());
        apiAverageDto.setApiAverageEnergy((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageEnergy).average().getAsDouble());
        apiAverageDto.setApiAverageRhr((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRhr).average().getAsDouble());
        apiAverageDto.setApiAverageStress((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRhr).average().getAsDouble());
        apiAverageDto.setApiAverageSleep((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageSleep).average().getAsDouble());
        apiAverageDto.setApiAverageLight((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageLight).average().getAsDouble());
        apiAverageDto.setApiAverageRem((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRem).average().getAsDouble());
        apiAverageDto.setApiAverageCore((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageCore).average().getAsDouble());
        apiAverageDto.setApiAverageDeep((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageDeep).average().getAsDouble());
        return apiAverageDto;
    }

    // 연별 유저 평균
    @Override
    public ApiAverageDto apiCustomAverage(int userAge, String Userex) {
        List<ApiAverage> apiAverageList = apiAverageRepo.findAll();
        ApiAverage apiAverage = apiAverageList.stream()
                .filter(i -> i.getSex().equals(Userex) && i.getAge() == userAge).collect(Collectors.toList()).get(0);
        return mapper.map(apiAverage, ApiAverageDto.class);
    }



//    @Override
//    public List<FriendDto> friendList(String userId) {
//        UserRepo.findById(userId).
//    }
//
//
//    @Override
//    public List<UserAverageDto> friendAverage(String userId) {
//        user
//    }


    // token에서 userId 추출. claims에서 subject안에 있는게 맞나?
    @Override
    public String getUserId(String token) {
//        Claims claims = Jwts.parserBuilder()
////                .setSigningKey(DatatypeConverter.parseBase64Binary("bundo10@naver.com"))
//                .setSigningKey("vita500")
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        String secretKey = "youcantrevealthesecretkey1234012300040hjbvjhbjhvhjbjkbhjvjbjkn";
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey.getBytes())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();

        return "2703629614";
    }
}
