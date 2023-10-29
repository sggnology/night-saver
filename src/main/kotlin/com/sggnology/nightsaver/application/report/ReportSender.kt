package com.sggnology.nightsaver.application.report

import com.sggnology.nightsaver.component.push.message.dto.FcmMessageDto
import com.sggnology.nightsaver.component.push.send.FcmSender
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import logger

class ReportSender(
    private val userInfoRepository: UserInfoRepository,
    private val fcmSender: FcmSender
) {

    fun send(
        carPlateNumber: String,
    ) {

        val user = userInfoRepository.findByCarPlateNumber(carPlateNumber)

        if (user == null) {
            logger.info("차량번호 : ${carPlateNumber} 에 대한 사용자가 존재하지 않아 신고에 대한 알림을 전송하지 않습니다.")
            return
        }

        val fcmToken = user.fcmInfoEntity?.fcmToken

        if(fcmToken == null){
            logger.info("사용자 : ${user.userId} 의 FCM 토큰이 존재하지 않아 신고에 대한 알림을 전송하지 않습니다.")
            return
        }

        logger.info("사용자 : ${user.userId} 에게 신고 알림을 보냅니다.")

        fcmSender.sendMulticastAsync(
            FcmMessageDto(
                title = "신고가 접수되었습니다.",
                message = "차량번호 : ${user.carPlateNumber} 에 대해 신고가 접수되었습니다.",
                tokens = listOf(fcmToken)
            )
        )

    }
}