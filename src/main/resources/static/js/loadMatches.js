$(document).ready(function() {
    var offset = 0; // 초기 offset 값 설정
    var limit = 10; // 초기 limit 값 설정
    var matchType = 50; // 초기 matchType 값 설정

    // '더보기' 버튼 클릭 이벤트 처리
    $('#loadMoreButton').click(function() {
        $.ajax({
            url: '/user/loadMoreMatches', // AJAX 요청을 보낼 URL
            type: 'GET', // HTTP 요청 방식 (GET)
            data: { matchType: matchType, offset: offset, limit: limit }, // 요청에 포함될 데이터
            success: function(response) {
                // AJAX 요청이 성공한 경우 실행되는 함수

                var matches = response; // 받아온 배열 데이터를 변수에 저장
                // 배열 길이 만큼 반복
                for (var i = 0; i < matches.length; i++) {
                    var match = matches[i];

                    // 경기 날짜를 지역화된 문자열로 변환
                    var formattedDate = new Date(match.matchDate).toLocaleString('ko-KR', {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: 'numeric',
                        minute: 'numeric'
                    });

                    var matchResult = match.matchInfo[0].matchDetail.matchResult;
                    var resultColor = matchResult === '승' ? 'blue' : (matchResult === '무' ? 'black' : 'red');
                    var resultText = '<span style="color: ' + resultColor + '">' + matchResult + '</span>';

                    // 경기 종료 타입에 따라 텍스트 반환
                    var matchEndTypeText = getMatchEndTypeText(match.matchInfo[0].matchDetail.matchEndType);

                    // 새로운 행 생성 및 데이터 추가
                    var newRow = '<tr>' +
                        '<td>' + formattedDate + '</td>' +
                        '<td>' + resultText + '</td>' +
                        '<td>' + match.matchInfo[0].nickname + '</td>' +
                        '<td>' + match.matchInfo[0].shoot.goalTotal + '</td>' +
                        '<td>' + match.matchInfo[1].nickname + '</td>' +
                        '<td>' + match.matchInfo[1].shoot.goalTotal + '</td>' +
                        '<td>' + matchEndTypeText + '</td>' +
                        '</tr>';

                    $('tbody').append(newRow); // 테이블의 tbody에 새로운 행 추가
                }

                offset += limit; // offset 값 업데이트
            },
            // AJAX 요청 실패시 실행되는 함수
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });

    // 경기 종료 타입에 따라 텍스트 반환
    function getMatchEndTypeText(matchEndType) {
        if (matchEndType === 0) {
            return '정상종료';
        } else if (matchEndType === 1) {
            return '몰수승';
        } else if (matchEndType === 2) {
            return '몰수패';
        } else {
            return '';
        }
    }
});
