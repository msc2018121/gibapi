
var mask = function() {
	var modal = document.getElementById('myModal');
	modal.style.display = "block";
}

var unmask = function() {
	var modal = document.getElementById('myModal');
	modal.style.display = "none";
}

function show(id) {
	var e = document.getElementById(id);
	e.style.display = "block";
}

function hide(id) {
	var e = document.getElementById(id);
	e.style.display = "none";
}

function setZandakaSampleInput1() {
	document.getElementById('zandakaAccountId').value = '00111111111';
}

function setZandakaSampleInput2() {
	document.getElementById('zandakaAccountId').value = '77717777777';
}

function setFurikomiSampleInput1() {
	document.getElementById('debitAccountBranchNo').value = '001';
	document.getElementById('debitAccountTypeCode').value = '1';
	document.getElementById('debitAccountNo').value = '1111111';
	document.getElementById('applicantName').value = 'エジソンショウジ';
	document.getElementById('paymentDate').value = '2016-07-15';
	document.getElementById('beneficiaryCode1').value = 'Ａ００１';
	document.getElementById('beneficiaryCode2').value = 'Ａ００１';
	document.getElementById('beneficiaryAccountFinancialInstitutionNo').value = '0005';
	document.getElementById('beneficiaryAccountBranchNo').value = '123';
	document.getElementById('beneficiaryAccountTypeCode').value = '2';
	document.getElementById('beneficiaryAccountNo').value = '1234567';
	document.getElementById('beneficiaryAccountNameKanji').value = '銀座利用者０７';
	document.getElementById('beneficiaryAccountNameKana').value = 'ギンザリヨウシャ０７';
	document.getElementById('notes').value = '振込メモは３２文字まで入力可能';
	document.getElementById('enteredAmount').value = '8000000';
	document.getElementById('chargesPaidBy').value = '1';
	document.getElementById('paymentApplicantNo').value = '1234567890';
	document.getElementById('ediInfo').value = '';
}

function setFurikomiSampleInput2() {
	document.getElementById('debitAccountBranchNo').value = '777';
	document.getElementById('debitAccountTypeCode').value = '1';
	document.getElementById('debitAccountNo').value = '7777777';
	document.getElementById('applicantName').value = 'テスラショウジ';
	document.getElementById('paymentDate').value = '2018-02-05';
	document.getElementById('beneficiaryCode1').value = 'Ｂ００１';
	document.getElementById('beneficiaryCode2').value = 'Ｂ００１';
	document.getElementById('beneficiaryAccountFinancialInstitutionNo').value = '1239';
	document.getElementById('beneficiaryAccountBranchNo').value = '909';
	document.getElementById('beneficiaryAccountTypeCode').value = '2';
	document.getElementById('beneficiaryAccountNo').value = '1234567';
	document.getElementById('beneficiaryAccountNameKanji').value = '品川利用者０７';
	document.getElementById('beneficiaryAccountNameKana').value = 'シナガワリヨウシャ０７';
	document.getElementById('notes').value = '保険金振込';
	document.getElementById('enteredAmount').value = '10000';
	document.getElementById('chargesPaidBy').value = '2';
	document.getElementById('paymentApplicantNo').value = '1234567897';
	document.getElementById('ediInfo').value = 'ＥＤＩ１２３４５６７８９０';
}

function switchDisplay(selectObject) {
	var value = selectObject.value;
	if (value == 'zandaka') {
		hide('nyushukkin');
		hide('furikomiMeisai');
		hide('furikomi');
		show(value);
	} else if (value == 'nyushukkin') {
		hide('zandaka');
		hide('furikomiMeisai');
		hide('furikomi');
		show(value);
	} else if (value == 'furikomiMeisai') {
		hide('nyushukkin');
		hide('zandaka');
		hide('furikomi');
		show(value);
	} else if (value == 'furikomi') {
		hide('nyushukkin');
		hide('furikomiMeisai');
		hide('zandaka');
		show(value);
	}
}

var gibApp = angular.module('gibApp', []);
gibApp.controller('gibAppController', function($scope, $http) {
	$scope.api = function() {
		var accountId = document.getElementById("zandakaAccountId").value;
		mask();
		$http({
			method : 'GET',
			url : 'APIServlet',
			params : {
				'ACCOUNT_ID' : accountId,
				'ACTION' : 'ZANDAKA_SHOUKAI'
			}
		}).then(function mySuccess(response) {
			$scope.zandaka = response.data;
			if (response.data.moreInformation != null || response.data.developerMessage != null || response.data.error != null || response.data.error_description != null || response.data.httpCode != null || response.data.httpMessage != null) {
				document.getElementById('zandakaMessage').style.color = "red";
				document.getElementById('zandakaMessage').innerHTML = 'エラー ' + JSON.stringify(response.data) + '。';
			} else {
				document.getElementById('zandakaMessage').style.color = "green";
				document.getElementById('zandakaMessage').innerHTML = 'リクエスト成功。';
			}
			unmask();
		}, function myError(response) {
			document.getElementById('zandakaMessage').style.color = "red";
			document.getElementById('zandakaMessage').innerHTML = 'サーバーエラー。';
			unmask();
		});
	}
});
gibApp.controller('zandakaController', function($scope, $http) {
	$scope.zandakaShoukai = function() {
		var accountId = document.getElementById("zandakaAccountId").value;
		mask();
		$http({
			method : 'GET',
			url : 'APIServlet',
			params : {
				'ACCOUNT_ID' : accountId,
				'ACTION' : 'ZANDAKA_SHOUKAI'
			}
		}).then(function mySuccess(response) {
			$scope.zandaka = response.data;
			if (response.data.moreInformation != null || response.data.developerMessage != null || response.data.error != null || response.data.error_description != null || response.data.httpCode != null || response.data.httpMessage != null) {
				document.getElementById('zandakaMessage').style.color = "red";
				document.getElementById('zandakaMessage').innerHTML = 'エラー ' + JSON.stringify(response.data) + '。';
			} else {
				document.getElementById('zandakaMessage').style.color = "green";
				document.getElementById('zandakaMessage').innerHTML = 'リクエスト成功。';
			}
			unmask();
		}, function myError(response) {
			document.getElementById('zandakaMessage').style.color = "red";
			document.getElementById('zandakaMessage').innerHTML = 'サーバーエラー。';
			unmask();
		});
	}
});
gibApp.controller('furikomiController', function($scope, $http) {
	$scope.furikomiShinsei = function() {
		var debitAccountBranchNo = document.getElementById('debitAccountBranchNo').value;
		var debitAccountTypeCode = document.getElementById('debitAccountTypeCode').value;
		var debitAccountNo = document.getElementById('debitAccountNo').value;
		var applicantName = document.getElementById('applicantName').value;
		var paymentDate = document.getElementById('paymentDate').value;
		var beneficiaryCode1 = document.getElementById('beneficiaryCode1').value;
		var beneficiaryCode2 = document.getElementById('beneficiaryCode2').value;
		var beneficiaryAccountFinancialInstitutionNo = document.getElementById('beneficiaryAccountFinancialInstitutionNo').value;
		var beneficiaryAccountBranchNo = document.getElementById('beneficiaryAccountBranchNo').value;
		var beneficiaryAccountTypeCode = document.getElementById('beneficiaryAccountTypeCode').value;
		var beneficiaryAccountNo = document.getElementById('beneficiaryAccountNo').value;
		var beneficiaryAccountNameKanji = document.getElementById('beneficiaryAccountNameKanji').value;
		var beneficiaryAccountNameKana = document.getElementById('beneficiaryAccountNameKana').value;
		var notes = document.getElementById('notes').value;
		var enteredAmount = document.getElementById('enteredAmount').value;
		var chargesPaidBy = document.getElementById('chargesPaidBy').value;
		var paymentApplicantNo = document.getElementById('paymentApplicantNo').value;
		var ediInfo = document.getElementById('ediInfo').value;
		mask();
		$http({
			method : 'POST',
			url : 'APIServlet',
			params : {
				'DEBIT_ACCOUNT_BRANCH_NO' : debitAccountBranchNo,
				'DEBIT_ACCOUNT_TYPE_CODE' : debitAccountTypeCode,
				'DEBIT_ACCOUNT_NO' : debitAccountNo,
				'APPLICANT_NAME' : applicantName,
				'PAYMENT_DATE' : paymentDate,
				'BENEFICIARY_CODE_1' : beneficiaryCode1,
				'BENEFICIARY_CODE_2' : beneficiaryCode2,
				'BENEFICIARY_ACCOUNT_FINANCIAL_INSTITUTION_NO' : beneficiaryAccountFinancialInstitutionNo,
				'BENEFICIARY_ACCOUNT_BRANCH_NO' : beneficiaryAccountBranchNo,
				'BENEFICIARY_ACCOUNT_TYPE_CODE' : beneficiaryAccountTypeCode,
				'BENEFICIARY_ACCOUNT_NO' : beneficiaryAccountNo,
				'BENEFICIARY_ACCOUNT_NAME_KANJI' : beneficiaryAccountNameKanji,
				'BENEFICIARY_ACCOUNT_NAME_KANA' : beneficiaryAccountNameKana,
				'NOTES' : notes,
				'ENTERED_AMOUNT' : enteredAmount,
				'CHARGES_PAID_BY' : chargesPaidBy,
				'PAYMENT_APPLICANT_NO' : paymentApplicantNo,
				'EDI_INFO' : ediInfo,
				'ACTION' : 'FURIKOMI_SHINSEI'
			}
		}).then(function mySuccess(response) {
			$scope.furikomi = response.data;
			if (response.data.moreInformation != null || response.data.developerMessage != null || response.data.error != null || response.data.error_description != null || response.data.httpCode != null || response.data.httpMessage != null) {
				document.getElementById('furikomiMessage').style.color = "red";
				document.getElementById('furikomiMessage').innerHTML = 'エラー ' + JSON.stringify(response.data) + '。';
			} else {
				document.getElementById('furikomiMessage').style.color = "green";
				document.getElementById('furikomiMessage').innerHTML = 'リクエスト成功。';
			}
			unmask();
		}, function myError(response) {
			document.getElementById('furikomiMessage').style.color = "red";
			document.getElementById('furikomiMessage').innerHTML = 'サーバーエラー。';
			unmask();
		});
	}
});