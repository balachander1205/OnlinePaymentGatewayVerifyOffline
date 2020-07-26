package com.dhfl.OnlinePaymentGatewayVerifyOffline.cron;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dhfl.OnlinePaymentGatewayVerifyOffline.config.ApplicationConfig;
import com.dhfl.OnlinePaymentGatewayVerifyOffline.entity.TransactionDetailsEntity;
import com.dhfl.OnlinePaymentGatewayVerifyOffline.service.TransactionDetailsInter;
import com.dhfl.OnlinePaymentGatewayVerifyOffline.util.PGIOfflineVerify;

@Component
public class OnlinePaymentGatewayOfflineVerifyCron {
	private static final Logger LOG = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);

	@Autowired
	ApplicationConfig config;
	
	@Autowired
	TransactionDetailsInter txndetailsInter;
	
	Logger logger = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);
	// @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
	@Scheduled(cron = "* * 1 * * ?")
	public void publish() {
		int count = 0;
		List<TransactionDetailsEntity> txndetails = txndetailsInter.getTxnReference("P");
		try {
			if(txndetails.size()>0) {
				for(TransactionDetailsEntity row : txndetails) {
					System.out.println(row.getClnt_txn_ref());
					String txnId = row.getClnt_txn_ref();
					String custName = row.getCustomer_name();
					String mobileNumber = row.getMobile_number();
					String loanCode = row.getLoan_code();
					Date txnDate = row.getTpsl_txn_time();				
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String fmtDate = dateFormat.format(txnDate);
					System.out.println(fmtDate+"|"+custName+"|"+mobileNumber);
					try {
						String message = PGIOfflineVerify.doOfflineVerify(config.getMerchantKey(), config.getMerchantIv(), 
								config.getMerchantWebServiceUrl(), txnId, config.getMerchantCode(), 
								config.getMerchantCur(), fmtDate, mobileNumber, custName);
						JSONObject responseObj = new JSONObject();
						responseObj.put("custName", custName);
						responseObj.put("mobileNumber", mobileNumber);
						responseObj.put("loanCode", loanCode);
						String clnt_rqst_meta = "{itc:"+loanCode+"}{mob:"+mobileNumber+"}{custname:"+custName+"}";
						responseObj.put("clnt_rqst_meta", clnt_rqst_meta);
						getTxnData(message, responseObj);
					}catch (Exception e) {
						logger.debug("Exception while offline verification="+e);
					}
				}
			}
		}catch (Exception e) {
			logger.debug("Exception@OnlinePaymentGatewayOfflineVerifyCron="+e);
		}
		LOG.info("Average value is 0::--->>"+count++);
	}
	
	public int getTxnData(String message, JSONObject responseObj) {
		try {
			String dataArr[] = message.split("\\|");			
			for(String val : dataArr) {
				String[] msgArr = val.split("=");
				try {
					if(msgArr.length>=1) {
						responseObj.put(msgArr[0], msgArr[1]);
					}
				}catch (Exception e) {
					responseObj.put(msgArr[0], "");
				}				
			}
			System.out.println(responseObj.toString());
			String txn_status = responseObj.getString("txn_status")!=null? responseObj.getString("txn_status"):"";
			String txn_msg = responseObj.getString("txn_msg")!=null? responseObj.getString("txn_msg"):"";
			String txn_err_msg = responseObj.getString("txn_err_msg")!=null? responseObj.getString("txn_err_msg"):"";
			String clnt_txn_ref = responseObj.getString("clnt_txn_ref")!=null? responseObj.getString("clnt_txn_ref"):"";
			String tpsl_bank_cd = responseObj.getString("tpsl_bank_cd")!=null? responseObj.getString("tpsl_bank_cd"):"";
			String tpsl_txn_id = responseObj.getString("tpsl_txn_id")!=null? responseObj.getString("tpsl_txn_id"):"";
			String txn_amt = responseObj.getString("txn_amt")!=null? responseObj.getString("txn_amt"):"";
			String tpsl_txn_time = responseObj.getString("tpsl_txn_time")!=null? responseObj.getString("tpsl_txn_time"):"";
			String bal_amt = responseObj.getString("bal_amt")!=null? responseObj.getString("bal_amt"):"";
			String rqst_token = responseObj.getString("rqst_token")!=null? responseObj.getString("rqst_token"):"";
			String clnt_rqst_meta = responseObj.getString("clnt_rqst_meta")!=null? responseObj.getString("clnt_rqst_meta"):"";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			if(tpsl_txn_time=="NA" || tpsl_txn_time=="") {
				long curTimeMiles = System.currentTimeMillis();
				Date curDateTime = new Date(curTimeMiles);
				tpsl_txn_time = dateFormat.format(curDateTime);
			}
			Date date = dateFormat.parse(tpsl_txn_time);
			txndetailsInter.updateTxnDetails(txn_status, txn_msg, txn_err_msg, 
					tpsl_bank_cd, tpsl_txn_id, txn_amt, clnt_rqst_meta, date, 
					bal_amt, "NA", "NA", "NA", "NA", 
					rqst_token, "NA", "S", clnt_txn_ref);
		} catch (Exception e) {
			logger.debug("Exception@updateTxnData="+e);
		}
		return 0;
	}
}
