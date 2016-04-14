package com.fd.payment.util;

import java.io.IOException;
import java.net.ConnectException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fd.util.StringUtil;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;

/**
 * @CreateDate: 2013-4-9 下午05:19:56
 * @author Longli
 * @Description: Paypal 工具类
 * 
 */
public class PaypalApiUtil {
	APIProfile profile = null;
	NVPCallerServices caller = null;
	static Properties prop = new Properties();
	
	public static List<String> local = new ArrayList<String>();
	static {
		String [] locals = new String[]{"AU","AT","BE","BR","CA","CH","CN","DE","ES","GB","FR","IT","NL","PL","PT","US"};
		for (String lo : locals) {
			local.add(lo);
		}
		try {
			prop.load(PaypalApiUtil.class.getClassLoader().getResourceAsStream("paypal.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static DateFormat format = new SimpleDateFormat("yyyy-M-d'T'HH:mm:ss'Z'");

	/**
	 * @return the profile
	 */
	public APIProfile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(APIProfile profile) {
		this.profile = profile;
	}
	
	public PaypalApiUtil(String apiUsername, String apiPassword, String apiSignature, String apiEnvironment, String account) throws Exception {
		init(apiUsername, apiPassword, apiSignature, apiEnvironment, account);
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	private void init(String apiUsername, String apiPassword, String apiSignature, String apiEnvironment, String account) throws Exception {
		profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(apiUsername);
		profile.setAPIPassword(apiPassword);
		profile.setSignature(apiSignature);

		// 沙箱环境值：sandbox；实际环境值：live
		profile.setEnvironment(apiEnvironment);

		if ("live".equals(apiEnvironment))
			profile.setSubject(account);
		else
			profile.setSubject("");

		caller = new NVPCallerServices();
		caller.setAPIProfile(profile);
	}

	/**
	 * TransactionSearch，搜索交易
	 * 
	 * @param startDate
	 *            搜索起始时间
	 * @param endDate
	 *            搜索结束时间
	 * @param orderNo
	 *            订单号
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String searchTransaction(String startDate, String endDate,
			String orderNo, NVPDecoder decoder) {
		NVPEncoder encoder = new NVPEncoder();
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "TransactionSearch");

				// Add request-specific fields to the request string.
				encoder.add("TRXTYPE", "Q");
				if (!StringUtil.isEmpty(startDate)) {
					encoder.add("STARTDATE", startDate);
				}

				if (!StringUtil.isEmpty(endDate)) {
					encoder.add("ENDDATE", endDate);
				}
				if (!StringUtil.isEmpty(orderNo))
					encoder.add("INVNUM", orderNo);
				// encoder.add("TRANSACTIONID", orderNo);

				// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse = caller.call(NVPRequest);
				// System.out.println("******IPN SEARCH:"
				// + URLDecoder.decode(NVPResponse, "utf-8"));
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * TransactionSearch，搜索交易
	 * 
	 * @param startDate
	 *            搜索起始时间
	 * @param endDate
	 *            搜索结束时间
	 * @param orderNo
	 *            订单号
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String searchTransaction(String startDate, String endDate,
			NVPEncoder encoder, NVPDecoder decoder) {
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "TransactionSearch");

				// Add request-specific fields to the request string.
				encoder.add("TRXTYPE", "Q");
				if (!StringUtil.isEmpty(startDate)) {
					encoder.add("STARTDATE", startDate);
				}

				if (!StringUtil.isEmpty(endDate)) {
					encoder.add("ENDDATE", endDate);
				}

				// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse = caller.call(NVPRequest);
				// System.out.println("******IPN SEARCH:"
				// + URLDecoder.decode(NVPResponse, "utf-8"));
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * GetTransactionDetails，获取交易明细
	 * 
	 * @param transactionId
	 *            交易号
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String getTransactionDetails(String transactionId, NVPDecoder decoder) {
		NVPEncoder encoder = new NVPEncoder();
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "GetTransactionDetails");

				// Add request-specific fields to the request string.
				encoder.add("TRANSACTIONID", transactionId);

				// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse = caller.call(NVPRequest);
				// System.out.println("******IPN DETAIL SEARCH:"
				// + URLDecoder.decode(NVPResponse, "utf-8"));
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * 使用SetExpressCheckout设置快速付款参数
	 * 
	 * @param encoder
	 *            提交参数，必须包括：RETURNURL、CANCELURL、AMT，具体内容参照Paypal API文档
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String setExpressCheckout(NVPEncoder encoder, NVPDecoder decoder) {
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				// Set up your API credentials, PayPal end point, API operation and
				// version.
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "SetExpressCheckout");

				// Add request-specific fields to the request string.
				// encoder.add("RETURNURL", returnURL);
				// encoder.add("CANCELURL", cancelURL);
				// encoder.add("AMT", amount);
				// encoder.add("PAYMENTACTION",paymentType);
				// encoder.add("CURRENCYCODE",currencyCode);

				// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse = caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * 使用GetExpressCheckoutDetails获取客户相关信息
	 * 
	 * @param token
	 *            来自SetExpressCheckout响应的值
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String getExpressCheckoutDetails(String token, NVPDecoder decoder) {
		NVPEncoder encoder = new NVPEncoder();
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "GetExpressCheckoutDetails");

				// Add request-specific fields to the request string.
				// Pass the token value returned in SetExpressCheckout.
				encoder.add("TOKEN", token);

				// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse = caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * 使用DoExpressCheckoutPayment完成付款动作
	 * 
	 * @param encoder
	 *            提交参数，具体内容参照Paypal API文档
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String doExpressCheckout(NVPEncoder encoder, NVPDecoder decoder) {
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "DoExpressCheckoutPayment");

				// Add request-specific fields to the request string.
				// Pass the token value by actual value returned in the
				// SetExpressCheckout.
				// encoder.add("TOKEN",token);
				// encoder.add("PAYERID",payerID);
				// encoder.add("AMT",amount);
				// encoder.add("PAYMENTACTION",paymentType);
				// encoder.add("CURRENCYCODE",currencyCode);
				// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse = caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * 使用DoCapture完成付款动作
	 * 
	 * @param encoder
	 *            提交参数，具体内容参照Paypal API文档
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String doCapture(NVPEncoder encoder, NVPDecoder decoder) {
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "DoCapture");

				String NVPRequest = encoder.encode();

				// Execute the API operation and obtain the response.
				String NVPResponse = caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}
	
	/**
	 * 使用DoVoid完成撤销付款动作
	 * 
	 * @param authId Paypal Authoriza时的交易号
	 * @param note 备注信息
	 * @param decoder
	 *            返回参数，具体内容参照Paypal API文档
	 * @return
	 */
	public String doVoid(String authId, String note, NVPDecoder decoder) {
		NVPEncoder encoder = new NVPEncoder();
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "DoVoid");

				encoder.add("AUTHORIZATIONID",authId);
				encoder.add("NOTE",note);
				encoder.add("TRXTYPE","V");
				
				String NVPRequest = encoder.encode();
				
				// Execute the API operation and obtain the response.
				String NVPResponse = caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * Paypal退款
	 * 
	 * @param refundType 退款类型：Full、Partial、Other
	 * @param transactionId 退款交易号
	 * @param amount 退款金额，当退款类型为Partial时，才需要设置，小数点两位
	 * @param note
	 * @param decoder
	 * @return
	 */
	public String refund(String refundType, String transactionId,
			String amount, String note, NVPDecoder decoder) {
		NVPEncoder encoder = new NVPEncoder();
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("METHOD", "RefundTransaction");

				encoder.add("REFUNDTYPE", refundType);
				encoder.add("TRANSACTIONID", transactionId);
				if ((refundType != null) && (refundType.length() > 0)
						&& (refundType.equalsIgnoreCase("Partial"))) {
					encoder.add("AMT", amount);
				}
				encoder.add("NOTE", note);

				String NVPRequest = encoder.encode();
				String NVPResponse = (String) caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}

	/**
	 * Paypal Deny Pending
	 * 
	 * @param transactionId 交易号
	 * @param decoder
	 * @return
	 */
	public String denyPending(String transactionId, NVPDecoder decoder) {
		NVPEncoder encoder = new NVPEncoder();
		String ack = null;
		while (StringUtil.isEmpty(ack)) {
			try {
				encoder.add("VERSION", "63.0");
				encoder.add("ACTION", "Deny");

				encoder.add("TRANSACTIONID", transactionId);

				String NVPRequest = encoder.encode();
				String NVPResponse = (String) caller.call(NVPRequest);
				decoder.decode(NVPResponse);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof ConnectException) {
					continue;
				}
			}
			ack = decoder.get("ACK");
			break;
		}
		return ack == null ? "" : ack;
	}
	
	public static String formatDate(Date date) {
		return format.format(date);
	}
}
