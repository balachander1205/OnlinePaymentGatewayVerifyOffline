package com.dhfl.OnlinePaymentGatewayVerifyOffline.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhfl.OnlinePaymentGatewayVerifyOffline.entity.TransactionDetailsEntity;
import com.dhfl.OnlinePaymentGatewayVerifyOffline.repo.TransactionDetailsRepo;

@Service
public class TransactionDetailsInterImpl implements TransactionDetailsInter{
	
	@Autowired
	TransactionDetailsRepo transDetailsRepo;

	@Override
	public List<TransactionDetailsEntity> getTxnReference(String txnNumber) {
		List<TransactionDetailsEntity> detailsEntity = transDetailsRepo.getTxnReference(txnNumber);
		return detailsEntity;
	}
	public int updateTxnDetails(String txn_status, String txn_msg,
			String txn_err_msg,String tpsl_bank_cd, 
			String tpsl_txn_id, String txn_amt, 
			String clnt_rqst_meta,Date tpsl_txn_time, 
			String bal_amt, String card_id, 
			String alias_name,  String BankTransactionID, 
			String mandate_reg_no,String token, 
			String hash,String clientTxnStatus,
			String clnt_txn_ref) {
		int count = transDetailsRepo.updateTxnDetails(txn_status, txn_msg, txn_err_msg, tpsl_bank_cd, tpsl_txn_id,
				txn_amt, clnt_rqst_meta, tpsl_txn_time, bal_amt, card_id, alias_name, BankTransactionID, mandate_reg_no,
				token, hash, clientTxnStatus, clnt_txn_ref);
		return count;
	}
	
}
