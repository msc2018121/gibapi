<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 銀行API アプリケーション</title>
<link rel="stylesheet" type="text/css" href="gib.css">
<style>
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="gib.js"></script>
</head>

<body ng-app="gibApp">
	<div id="container">
		<div class="top">
			<p> 銀行API アプリケーション (サンプル)
			<div>
				<span>API: 三菱東京UFJ 法人API体験コース</span>
			</div>
		</div>
		<hr>
		<div class="left ">
			<select class="action" onchange="switchDisplay(this)">
				<option value="zandaka">残高照会</option>
				<!-- 				<option value="nyushukkin">入出金明細照会</option>
				<option value="furikomiMeisai">振込入金明細</option> -->
				<option value="furikomi">振込申請</option>
			</select>
		</div>

		<div class="middle">
			<div class="right">
				<div style="display: block" id="zandaka"
					ng-controller="zandakaController">
					<div>残高照会 - 指定した口座の残高（当日残高、前日残高、前月末残高）を照会します。</div>
					<div class="verticalSpace"></div>
					<div class="underline">入力</div>
					<div>
						<button type="button" onclick="setZandakaSampleInput1()">入力サンプル
							１</button>
						<button type="button" onclick="setZandakaSampleInput2()">入力サンプル
							２</button>
					</div>
					<div>
						<label>口座ID: </label><input type="text" id="zandakaAccountId">
					</div>
					<div class="verticalSpace"></div>
					<div>
						<button class="submitButton" ng-click="zandakaShoukai()">送信</button>
					</div>
					<div class="verticalSpace"></div>
					<div class="underline">結果</div>
					<div><label> サーバーメッセージ: </label><label id="zandakaMessage"></label></div>
					<div>支店番号: {{zandaka.branchNo}}</div>
					<div>支店名: {{zandaka.branchName}}</div>
					<div>科目コード: {{zandaka.accountTypeCode}}</div>
					<div>科目名: {{zandaka.accountTypeName}}</div>
					<div>口座番号: {{zandaka.accountNo}}</div>
					<div>口座名義（漢字）: {{zandaka.accountNameKanji}}</div>
					<div>口座名義（カナ）: {{zandaka.accountNameKana}}</div>
					<div>操作日: {{zandaka.operationDate}}</div>
					<div>操作時刻: {{zandaka.operationTime}}</div>
					<div>当日残高: {{zandaka.todayBalance}}</div>
					<div>資金化残高: {{zandaka.clearedBalance}}</div>
					<div>前日残高基準日: {{zandaka.previousDate}}</div>
					<div>前日残高: {{zandaka.previousDayBalance}}</div>
					<div>前月末残高基準日: {{zandaka.previousMonthEndDate}}</div>
					<div>前月末残高: {{zandaka.previousMonthEndBalance}}</div>
				</div>
				<div style="display: none" id="nyushukkin"
					ng-controller="gibAppController">
					<div>入出金明細照会 - 指定した口座の全ての明細を照会します。</div>
					<div class="verticalSpace"></div>
					<div class="underline">入力</div>
					<div>
						<label>口座ID: </label><input type="text" name="FirstName">
					</div>
					<div>
						<label>照会期間(FROM): </label><input id="1" type="date"
							placeholder="YYYY-MM-DD">
					</div>
					<div>
						<label>照会期間(TO): </label><input id="2" type="date">
					</div>
					<div>
						<label>次明細取得キーワード: </label><input type="text" name="FirstName">
					</div>
					<div class="verticalSpace"></div>
					<div>
						<button class="submitButton" ng-click="api()">送信</button>
					</div>
					<div class="verticalSpace"></div>
					<div class="underline">結果</div>
					<div>次明細取得キーワード: {{nyushukkin.nextKeyword}}</div>
					<div>件数: {{nyushukkin.number}}</div>
					<div>返却明細期間(FROM): {{nyushukkin.transactionDateFrom}}</div>
					<div>返却明細期間(TO): {{nyushukkin.transactionDateTo}}</div>
					<div>異動明細番号(1明細目): {{nyushukkin.transactionIdFirst}}</div>
					<div>異動明細番号(最終明細): {{nyushukkin.transactionIdLast}}</div>
					<div>操作日: {{nyushukkin.operationDate}}</div>
					<div>操作時刻: {{nyushukkin.operationTime}}</div>
					<div>共通口座情報: {{nyushukkin.accountInfo}}</div>
					<div>入出金明細配列: {{nyushukkin.transactions}}</div>
				</div>
				<div style="display: none" id="furikomiMeisai"
					ng-controller="gibAppController">
					<div>振込入金明細照会 - 指定した口座の、振込入金の明細を照会します。</div>
					<div class="verticalSpace"></div>
					<div class="underline">入力</div>
					<div>
						<label>口座ID: </label><input type="text" name="FirstName">
					</div>
					<div>
						<label>照会期間(FROM): </label><input id="1" type="date"
							placeholder="YYYY-MM-DD">
					</div>
					<div>
						<label>照会期間(TO): </label><input id="2" type="date">
					</div>
					<div>
						<label>次明細取得キーワード: </label><input type="text" name="FirstName">
					</div>
					<div class="verticalSpace"></div>
					<div>
						<button class="submitButton" ng-click="api()">送信</button>
					</div>
					<div class="verticalSpace"></div>
					<div class="underline">結果</div>
					<div>次明細取得キーワード: {{furikomiMeisai.nextKeyword}}</div>
					<div>件数: {{furikomiMeisai.number}}</div>
					<div>返却明細期間(FROM): {{furikomiMeisai.transactionDateFrom}}</div>
					<div>返却明細期間(TO): {{furikomiMeisai.transactionDateTo}}</div>
					<div>異動明細番号(1明細目): {{furikomiMeisai.transactionIdFirst}}</div>
					<div>異動明細番号(最終明細): {{furikomiMeisai.transactionIdLast}}</div>
					<div>操作日: {{furikomiMeisai.operationDate}}</div>
					<div>操作時刻: {{furikomiMeisai.operationTime}}</div>
					<div>共通口座情報: {{furikomiMeisai.accountInfo}}</div>
					<div>入出金明細配列: {{furikomiMeisai.paymentArrivals}}</div>
				</div>

				<div style="display: none" id="furikomi" ng-controller="furikomiController">
					<div>振込申請 - BizSTATIONに登録したサービス指定口座から、任意の口座への振込を申請します。</div>
					<div class="verticalSpace"></div>
					<div class="underline">入力</div>
					<div>
						<button type="button" onclick="setFurikomiSampleInput1()">入力サンプル
							１</button>
						<button type="button" onclick="setFurikomiSampleInput2()">入力サンプル
							２</button>
					</div>
					<div>
						<label>引落口座 支店番号: </label><input type="text"
							id="debitAccountBranchNo"><label>（半角数字 ）</label>
					</div>
					<div>
						<label>引落口座 科目コード（1:普通、2:当座、5:通知、6:定期）: </label><input type="text"
							id="debitAccountTypeCode">
					</div>
					<div>
						<label>引落口座 口座番号: </label><input type="text" id="debitAccountNo"><label>（半角数字 ）</label>
					</div>
					<div>
						<label>依頼人名: </label><input type="text" id="applicantName"><label>（全角数字、全角英字、全角カナ）</label>
					</div>
					<div>
						<label>振込指定日: </label><input id="paymentDate" type="date">
					</div>
					<div>
						<label>振込先コード1: </label><input id="beneficiaryCode1" type="text"><label>（全角数字、全角英字、全角カナ）</label>
					</div>
					<div>
						<label>振込先コード2: </label><input id="beneficiaryCode2" type="text"><label>（全角数字、全角英字、全角カナ）</label>
					</div>
					<div>
						<label>振込先 金融機関番号: </label><input
							id="beneficiaryAccountFinancialInstitutionNo" type="text"><label>（半角数字 ）</label>
					</div>
					<div>
						<label>振込先 支店番号: </label><input id="beneficiaryAccountBranchNo"
							type="text"><label>（半角数字 ）</label>
					</div>
					<div>
						<label>振込先 科目コード（1:普通、2:当座、4:貯蓄、5:通知、6:定期、9:その他）: </label><input
							id="beneficiaryAccountTypeCode" type="text">
					</div>
					<div>
						<label>振込先 口座番号: </label><input id="beneficiaryAccountNo"
							type="text"><label>（半角数字 ）</label>
					</div>
					<div>
						<label>振込先 口座名義(漢字): </label><input
							id="beneficiaryAccountNameKanji" type="text"><label>（全角文字すべて ）</label>
					</div>
					<div>
						<label>振込先 口座名義(カナ): </label><input
							id="beneficiaryAccountNameKana" type="text"><label>（全角数字、全角英字、全角カナ ）</label>
					</div>
					<div>
						<label>振込メモ: </label><input id="notes" type="text"><label>（全角文字すべて）</label>
					</div>
					<div>
						<label>入力金額: </label><input id="enteredAmount" type="text"><label>（0～999999999999）</label>
					</div>
					<div>
						<label>手数料負担区分: </label><input id="chargesPaidBy" type="text">
					</div>
					<div>
						<label>振込依頼人番号: </label><input id="paymentApplicantNo" type="text"><label>（半角数字）</label>
					</div>
					<div>
						<label>EDI情報: </label><input id="ediInfo" type="text"><label>（全角数字、全角英字、全角カナ）</label>
					</div>
					<div class="verticalSpace"></div>
					<div>
						<!-- <button class="submitButton" 　ng-click="furikomiShinsei()">送信</button> -->
						<button class="submitButton" ng-click="furikomiShinsei()">送信</button>
					</div>
					<div class="verticalSpace"></div>
					<div class="underline">結果</div>
					<div><label> サーバーメッセージ: </label><label id="furikomiMessage"></label></div>
					<div>引落口座 支店番号:
						{{furikomi.debitAccountInfo.debitAccountBranchNo}}</div>
					<div>引落口座 支店名:
						{{furikomi.debitAccountInfo.debitAccountBranchName}}</div>
					<div>引落口座 科目コード:
						{{furikomi.debitAccountInfo.debitAccountAccountTypeCode}}</div>
					<div>引落口座 科目名:
						{{furikomi.debitAccountInfo.debitAccountAccountTypeName}}</div>
					<div>引落元となる口座の口座番号:
						{{furikomi.debitAccountInfo.debitAccountAccountNo}}</div>
					<div>引落口座 口座名義（漢字）:
						{{furikomi.debitAccountInfo.debitAccountNameKanji}}</div>
					<div>引落口座 口座名義（カナ）:
						{{furikomi.debitAccountInfo.debitAccountNameKana}}</div>
					<div>依頼人名: {{furikomi.applicantName}}</div>
					<div>振込指定日: {{furikomi.paymentDate}}</div>
					<div>振込先コード1: {{furikomi.beneficiaryCode1}}</div>
					<div>振込先コード2: {{furikomi.beneficiaryCode2}}</div>
					<div>振込先 金融機関番号:
						{{furikomi.beneficiaryInfo.beneficiaryAccountFinancialInstitutionNo}}</div>
					<div>振込先 金融機関名:
						{{furikomi.beneficiaryInfo.beneficiaryAccountFinancialInstitutionName}}</div>
					<div>振込先 支店番号:
						{{furikomi.beneficiaryInfo.beneficiaryAccountBranchNo}}</div>
					<div>振込先 支店名:
						{{furikomi.beneficiaryInfo.beneficiaryAccountBranchName}}</div>
					<div>振込先 科目コード（1:普通、2:当座、4:貯蓄、5:通知、6:定期、9:その他）:
						{{furikomi.beneficiaryInfo.beneficiaryAccountTypeCode}}</div>
					<div>振込先 科目名:
						{{furikomi.beneficiaryInfo.beneficiaryAccountTypeName}}</div>
					<div>振込先 口座番号:
						{{furikomi.beneficiaryInfo.beneficiaryAccountNo}}</div>
					<div>振込先 口座名義(漢字):
						{{furikomi.beneficiaryInfo.beneficiaryAccountTypeName}}</div>
					<div>振込先 口座名義(カナ):
						{{furikomi.beneficiaryInfo.beneficiaryAccountNo}}</div>
					<div>振込メモ: {{furikomi.notes}}</div>
					<div>入力金額: {{furikomi.enteredAmount}}</div>
					<div>振込金額: {{furikomi.paymentAmount}}</div>
					<div>振込手数料（銀行宛支払手数料）: {{furikomi.paymentCharge}}</div>
					<div>手数料負担区分: {{furikomi.chargesPaidBy}}</div>
					<div>先方負担手数料: {{furikomi.chargeToBeneficiary}}</div>
					<div>振込依頼人番号: {{furikomi.paymentApplicantNo}}</div>
					<div>EDI情報: {{furikomi.ediInfo}}</div>
					<div>振込ID: {{furikomi.transferId}}</div>
					<div>ステータス: {{furikomi.status}}</div>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal" class="modal">
		<div class="modal-content">
			<p>処理中...</p>
		</div>
	</div>
</body>
</html>