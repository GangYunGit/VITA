package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.Rhr.RhrDailyDto;
import com.ssafy.vitawearable.dto.Rhr.RhrMonthlyDto;
import com.ssafy.vitawearable.dto.Rhr.RhrPastAndNowDto;
import com.ssafy.vitawearable.dto.Rhr.RhrWeeklyDto;
import com.ssafy.vitawearable.service.Wearable;
import com.ssafy.vitawearable.service.WearablePast;
import com.ssafy.vitawearable.util.HeaderUtil;
import com.ssafy.vitawearable.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("웨어러블 심박수 컨트롤러 API")
@RequestMapping("/api/wearable/user/rhr")
public class RhrController {
    private final Wearable wearable;
    private final WearablePast wearablePast;
    private final UserUtil userUtil;

    // 심박수 월별 데이터
    @ApiOperation(
            value = "심박수 월별 데이터 요청",
            notes = "userId를 통해 심박수 월별 데이터를 json 형태로 반환한다",
            response = RhrMonthlyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/monthly")
    public ResponseEntity<List<RhrMonthlyDto>> rhrMonthly(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.rhrMonthly(userId), HttpStatus.valueOf(200));
    }

    // 심박수 주간 데이터
    @ApiOperation(
            value = "심박수 주별 데이터 요청",
            notes = "userId를 통해 심박수 주별 데이터를 json 형태로 반환한다",
            response = RhrWeeklyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/weekly")
    public ResponseEntity<List<RhrWeeklyDto>> rhrWeekly(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.rhrWeekly(userId), HttpStatus.valueOf(200));
    }

    // 심박수 일간 데이터
    @ApiOperation(
            value = "심박수 일별 데이터 요청",
            notes = "userId를 통해 심박수 일별 데이터를 json 형태로 반환한다",
            response = RhrDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/daily")
    public ResponseEntity<List<RhrDailyDto>> rhrDaily(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.rhrDaily(userId), HttpStatus.valueOf(200));
    }

    // 심박수 과거 비교 데이터
    @ApiOperation(
            value = "심박수 과거,현재 데이터 요청",
            notes = "userId를 통해 심박수 과거,현재 데이터를 json 형태로 반환한다",
            response = RhrPastAndNowDto.class
    )
    @GetMapping("/past")
    public ResponseEntity<RhrPastAndNowDto> rhrPast(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearablePast.rhrPastAndNow(userId), HttpStatus.valueOf(200));
    }
}
