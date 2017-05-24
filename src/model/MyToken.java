package model;
/*
 * accessToken token过期时间
 */
public class MyToken {
	private String tokenId = "";
	private String accessToken = "";
	private Long tokenEndTime;
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getTokenEndTime() {
		return tokenEndTime;
	}
	public void setTokenEndTime(Long tokenEndTime) {
		this.tokenEndTime = tokenEndTime;
	}
	
	
}
