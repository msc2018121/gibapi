package com.msc.gib.app.mufg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class APIServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/APIServlet" })
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String key = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		OkHttpClient client = new OkHttpClient();
		Request apiRequest = null;

		if (Shared.MUFG_ACTIONS.ZANDAKA_SHOUKAI.toString()
				.equals(request.getParameter(Shared.PARAMETERS.ACTION.toString()))) {
			String accountId = request.getParameter(Shared.PARAMETERS.ACCOUNT_ID.toString());
			apiRequest = new Request.Builder()
					.url("https://dev.mu.jp/btmu/corporation/trial/v1/accounts/" + accountId).get()
					.addHeader("x-ibm-client-id", key)
					.addHeader("accept", "application/json").addHeader("x-btmu-seq-no", "20180123-1111111111111111")
					.build();
		} else if (Shared.MUFG_ACTIONS.NYUSHUKKIN_MEISAI.toString()
				//TODO 必要ならばあとで
				.equals(request.getParameter(Shared.PARAMETERS.ACTION.toString()))) {
			String accountNumber = request.getParameter(Shared.PARAMETERS.ACCOUNT_ID.toString());
			String inquiryDateFrom = request.getParameter(Shared.PARAMETERS.INQUIRY_DATE_FROM.toString());
			String inquiryDateTo = request.getParameter(Shared.PARAMETERS.INQUIRY_DATE_TO.toString());
			// String nextKeyword =
			// request.getParameter(Shared.PARAMETERS.NEXT_KEYWORD.toString());
			apiRequest = new Request.Builder()
					.url("https://dev.mu.jp/btmu/corporation/trial/v1/accounts/" + accountNumber
							+ "/transactions?inquiryDateFrom=" + inquiryDateFrom + "&inquiryDateTo=" + inquiryDateTo)// +"&nextKeyword="+nextKeyword)
					.get().addHeader("x-ibm-client-id", key)
					.addHeader("accept", "application/json").addHeader("x-btmu-seq-no", "20180123-1111111111111111")
					.build();
		} else if (Shared.MUFG_ACTIONS.FURIKOMI_NYUSHUKKIN_MEISAI.toString()
				.equals(request.getParameter(Shared.PARAMETERS.ACTION.toString()))) {
			//TODO 必要ならばあとで
			String accountNumber = request.getParameter(Shared.PARAMETERS.ACCOUNT_ID.toString());
			String inquiryDateFrom = request.getParameter(Shared.PARAMETERS.INQUIRY_DATE_FROM.toString());
			String inquiryDateTo = request.getParameter(Shared.PARAMETERS.INQUIRY_DATE_TO.toString());
			// String nextKeyword =
			// request.getParameter(Shared.PARAMETERS.NEXT_KEYWORD.toString());
			apiRequest = new Request.Builder()
					.url("https://dev.mu.jp/btmu/corporation/trial/v1/accounts/" + accountNumber
							+ "/transactions/paymentarrivals?inquiryDateFrom=" + inquiryDateFrom + "&inquiryDateTo="
							+ inquiryDateTo)// +"&nextKeyword="+nextKeyword)
					.get().addHeader("x-ibm-client-id", key)
					.addHeader("accept", "application/json").addHeader("x-btmu-seq-no", "20180123-1111111111111111")
					.build();
		} else if (Shared.MUFG_ACTIONS.FURIKOMI_SHINSEI.toString()
				.equals(request.getParameter(Shared.PARAMETERS.ACTION.toString()))) {
			MediaType mediaType = MediaType.parse("application/json");
			// RequestBody body = RequestBody.create(mediaType,
			// "{\"debitAccountInfo\":{\"debitAccountBranchNo\":\"001\",\"debitAccountTypeCode\":\"1\",\"debitAccountNo\":\"1111111\"},\"applicantName\":\"エジソンショウジ\",\"paymentDate\":\"2016-07-15\",\"beneficiaryCode1\":\"Ａ００１\",\"beneficiaryCode2\":\"Ａ００１\",\"beneficiaryInfo\":{\"beneficiaryAccountFinancialInstitutionNo\":\"0005\",\"beneficiaryAccountBranchNo\":\"123\",\"beneficiaryAccountTypeCode\":\"2\",\"beneficiaryAccountNo\":\"1234567\",\"beneficiaryAccountNameKanji\":\"銀座利用者０７\",\"beneficiaryAccountNameKana\":\"ギンザリヨウシャ０７\"},\"notes\":\"振込メモは３２文字まで入力可能\",\"enteredAmount\":8000000,\"chargesPaidBy\":\"1\",\"paymentApplicantNo\":\"1234567890\",\"ediInfo\":\"ＥＤＩ１２３４５６７８９０\"}");
			String debitAccountBranchNo = request.getParameter(Shared.PARAMETERS.DEBIT_ACCOUNT_BRANCH_NO.toString());
			String debitAccountTypeCode = request.getParameter(Shared.PARAMETERS.DEBIT_ACCOUNT_TYPE_CODE.toString());
			String debitAccountNo = request.getParameter(Shared.PARAMETERS.DEBIT_ACCOUNT_NO.toString());
			String applicantName = request.getParameter(Shared.PARAMETERS.APPLICANT_NAME.toString());
			byte[] bytes = applicantName.getBytes(StandardCharsets.ISO_8859_1);
			applicantName = new String(bytes, StandardCharsets.UTF_8);
			String paymentDate = request.getParameter(Shared.PARAMETERS.PAYMENT_DATE.toString());
			String beneficiaryCode1 = request.getParameter(Shared.PARAMETERS.BENEFICIARY_CODE_1.toString());
			bytes = beneficiaryCode1.getBytes(StandardCharsets.ISO_8859_1);
			beneficiaryCode1 = new String(bytes, StandardCharsets.UTF_8);
			String beneficiaryCode2 = request.getParameter(Shared.PARAMETERS.BENEFICIARY_CODE_2.toString());
			bytes = beneficiaryCode2.getBytes(StandardCharsets.ISO_8859_1);
			beneficiaryCode2 = new String(bytes, StandardCharsets.UTF_8);
			String beneficiaryAccountFinancialInstitutionNo = request
					.getParameter(Shared.PARAMETERS.BENEFICIARY_ACCOUNT_FINANCIAL_INSTITUTION_NO.toString());
			String beneficiaryAccountBranchNo = request
					.getParameter(Shared.PARAMETERS.BENEFICIARY_ACCOUNT_BRANCH_NO.toString());
			String beneficiaryAccountTypeCode = request
					.getParameter(Shared.PARAMETERS.BENEFICIARY_ACCOUNT_TYPE_CODE.toString());
			String beneficiaryAccountNo = request.getParameter(Shared.PARAMETERS.BENEFICIARY_ACCOUNT_NO.toString());
			String beneficiaryAccountNameKanji = request
					.getParameter(Shared.PARAMETERS.BENEFICIARY_ACCOUNT_NAME_KANJI.toString());
			bytes = beneficiaryAccountNameKanji.getBytes(StandardCharsets.ISO_8859_1);
			beneficiaryAccountNameKanji = new String(bytes, StandardCharsets.UTF_8);
			String beneficiaryAccountNameKana = request
					.getParameter(Shared.PARAMETERS.BENEFICIARY_ACCOUNT_NAME_KANA.toString());
			bytes = beneficiaryAccountNameKana.getBytes(StandardCharsets.ISO_8859_1);
			beneficiaryAccountNameKana = new String(bytes, StandardCharsets.UTF_8);
			String notes = request.getParameter(Shared.PARAMETERS.NOTES.toString());
			bytes = notes.getBytes(StandardCharsets.ISO_8859_1);
			notes = new String(bytes, StandardCharsets.UTF_8);
			String enteredAmount = request.getParameter(Shared.PARAMETERS.ENTERED_AMOUNT.toString());
			String chargesPaidBy = request.getParameter(Shared.PARAMETERS.CHARGES_PAID_BY.toString());
			String paymentApplicantNo = request.getParameter(Shared.PARAMETERS.PAYMENT_APPLICANT_NO.toString());
			String ediInfo = request.getParameter(Shared.PARAMETERS.EDI_INFO.toString());
			bytes = ediInfo.getBytes(StandardCharsets.ISO_8859_1);
			ediInfo = new String(bytes, StandardCharsets.UTF_8);

			RequestBody body = RequestBody.create(mediaType, "{\"debitAccountInfo\":{\"debitAccountBranchNo\":\""
					+ debitAccountBranchNo + "\",\"debitAccountTypeCode\":\"" + debitAccountTypeCode
					+ "\",\"debitAccountNo\":\"" + debitAccountNo + "\"},\"applicantName\":\"" + applicantName
					+ "\",\"paymentDate\":\"" + paymentDate + "\",\"beneficiaryCode1\":\"" + beneficiaryCode1
					+ "\",\"beneficiaryCode2\":\"" + beneficiaryCode2
					+ "\",\"beneficiaryInfo\":{\"beneficiaryAccountFinancialInstitutionNo\":\""
					+ beneficiaryAccountFinancialInstitutionNo + "\",\"beneficiaryAccountBranchNo\":\""
					+ beneficiaryAccountBranchNo + "\",\"beneficiaryAccountTypeCode\":\"" + beneficiaryAccountTypeCode
					+ "\",\"beneficiaryAccountNo\":\"" + beneficiaryAccountNo + "\",\"beneficiaryAccountNameKanji\":\""
					+ beneficiaryAccountNameKanji + "\",\"beneficiaryAccountNameKana\":\"" + beneficiaryAccountNameKana
					+ "\"},\"notes\":\"" + notes + "\",\"enteredAmount\":" + enteredAmount + ",\"chargesPaidBy\":\""
					+ chargesPaidBy + "\"  "+createTail(paymentApplicantNo, ediInfo)+"}");

			// RequestBody body = RequestBody.create(mediaType,
			// "{\"debitAccountInfo\":{\"debitAccountBranchNo\":\"001\",\"debitAccountTypeCode\":\"1\",\"debitAccountNo\":\"1111111\"},\"applicantName\":\"エジソンショウジ\",\"paymentDate\":\"2016-07-15\",\"beneficiaryCode1\":\"Ａ００１\",\"beneficiaryCode2\":\"Ａ００１\",\"beneficiaryInfo\":{\"beneficiaryAccountFinancialInstitutionNo\":\"0005\",\"beneficiaryAccountBranchNo\":\"123\",\"beneficiaryAccountTypeCode\":\"2\",\"beneficiaryAccountNo\":\"1234567\",\"beneficiaryAccountNameKanji\":\"銀座利用者０７\",\"beneficiaryAccountNameKana\":\"ギンザリヨウシャ０７\"},\"notes\":\"振込メモは３２文字まで入力可能\",\"enteredAmount\":8000000,\"chargesPaidBy\":\"1\",\"paymentApplicantNo\":\"1234567890\"}");

			apiRequest = new Request.Builder()
					.url("https://dev.mu.jp/btmu/corporation/trial/v1/me/transfers").post(body)
					.addHeader("x-ibm-client-id", key)
					.addHeader("content-type", "application/json").addHeader("accept", "application/json")
					.addHeader("x-btmu-seq-no", "20180123-1111111111111111").build();
		} else {
			return;
		}

		Response apiResponse = null;
		try {
			apiResponse = client.newCall(apiRequest).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO
		String body = null;
		try {
			body = apiResponse.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(body);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String createTail(String paymentApplicantNo, String ediInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		// ,\"paymentApplicantNo\":\"" + paymentApplicantNo +
		// "\",\"ediInfo\":\""
		// + ediInfo + "\"
		if ((null != paymentApplicantNo && !paymentApplicantNo.trim().isEmpty())) {
			stringBuilder.append(",\"paymentApplicantNo\":\"" + paymentApplicantNo + "\"");
		}
		if ((null != ediInfo && !ediInfo.trim().isEmpty())) {
			stringBuilder.append(",\"ediInfo\":\"" + ediInfo + "\"");
		}
		return stringBuilder.toString();
	}
}
