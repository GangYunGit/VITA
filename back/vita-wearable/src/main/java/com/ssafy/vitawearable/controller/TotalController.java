package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.TotalScoreDto;
import com.ssafy.vitawearable.entity.TotalScore;
import com.ssafy.vitawearable.repo.TotalScoreRepo;
import com.ssafy.vitawearable.service.Wearable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wearable")
public class TotalController {
    private final TotalScoreRepo totalScoreRepo;
    private final Wearable wearable;

    // 종합 점수 반환(최근것을 왼쪽에, 나머지 5개를 오른쪽에 배치)
    @GetMapping("/score")
    public ResponseEntity<List<TotalScoreDto>> totalScore(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
//        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(wearable.totalScore(userId), HttpStatus.valueOf(200));
    }

    // 연도별 데일리 종합 점수
    @GetMapping("/user/score/{year}")
    public ResponseEntity<List<?>> totalDailyScore(@RequestHeader("token") String token, @PathVariable int year) {
        String userId = wearable.getUserId(token);
//        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(wearable.yearTotalScore(userId,year),HttpStatus.valueOf(200));
    }

    @GetMapping("/")
    public void test() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(
                2023,3,21,0,0,0,0, ZoneId.of("Asia/Seoul")
        );
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(
                2023,3,15,0,0,0,0, ZoneId.of("Asia/Seoul")
        );
        ZonedDateTime zonedDateTime3 = ZonedDateTime.of(
                2023,3,31,0,0,0,0, ZoneId.of("Asia/Seoul")
        );
        ZonedDateTime zonedDateTime4 = ZonedDateTime.of(
                2023,4,1,0,0,0,0, ZoneId.of("Asia/Seoul")
        );
        DateTimeFormatter equalWeekFormatter = DateTimeFormatter.ofPattern("yyyy w");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd / W / F / w ");
        System.out.println(zonedDateTime.format(equalWeekFormatter));
        System.out.println(zonedDateTime1.format(equalWeekFormatter));
        System.out.println(zonedDateTime2.format(equalWeekFormatter));
        System.out.println(zonedDateTime3.format(equalWeekFormatter));
        System.out.println(zonedDateTime4.format(equalWeekFormatter));
    }
}
