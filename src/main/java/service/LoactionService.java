package service;

import models.ShopDetail;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface LoactionService {
	public ResponseEntity<ShopDetail> getShopLoction(RequestEntity<ShopDetail> shopeDetail,Double latitude,Double longitude);


}
