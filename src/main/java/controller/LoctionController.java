package controller;

import java.net.URISyntaxException;

import models.ShopDetail;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.LoactionService;

@RestController
public class LoctionController implements LoactionService {
	private GeocodingController geocodingController;

	/*
	 * Get the Nearest location of the shop as per latitiude and longitude of 
	 * customer .  
 * */
	@RequestMapping(value = "/shoploaction", method = RequestMethod.POST)
	public ResponseEntity<ShopDetail> getShopLoction(
			RequestEntity<ShopDetail> shopDetail,
			@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude) {
		geocodingController = new GeocodingController();
		ShopDetail shopDtl = new ShopDetail();
		shopDtl.setShopName(shopDetail.getBody().getShopName());
		shopDtl.setShopAddress(shopDetail.getBody().getShopAddress());

		ShopDetail nearestShopDetails = null;
		ResponseEntity<ShopDetail> responseEntity = null;
		try {

			nearestShopDetails = geocodingController.getGeocoding(shopDtl,
					latitude, longitude);
			if (null == nearestShopDetails) {
				responseEntity = new ResponseEntity<ShopDetail>(
						nearestShopDetails, HttpStatus.CONFLICT);
			} else {
				responseEntity = new ResponseEntity<ShopDetail>(
						nearestShopDetails, HttpStatus.OK);
			}
		} catch (URISyntaxException e) {
			// Log the exception
			e.printStackTrace();
		}

		return responseEntity;

	}

}
