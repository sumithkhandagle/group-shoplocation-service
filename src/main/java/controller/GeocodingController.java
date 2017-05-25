package controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.ShopAddress;
import models.ShopDetail;

import org.springframework.web.client.RestTemplate;

public class GeocodingController {
	
	public  ShopDetail getGeocoding(ShopDetail shope,Double latitude,Double longitude) throws URISyntaxException {

		String uri = "https://maps.googleapis.com/maps/api/geocode/json?address="
				+ shope.getShopName() + "&number=" + shope.getShopAddress().getNumber() + "postal_code=" + shope.getShopAddress().getPostCode();
		RestTemplate restTemplate = new RestTemplate();
		List<ShopDetail> shopDetails = new ArrayList<ShopDetail>();
		Map<?, ?> obj = restTemplate.getForObject(uri, Map.class);
		List<?> results = (List<?>) obj.get("results");
		for (int i = 0; i < results.size(); i++) {
			ShopDetail shopDetail = new ShopDetail();
			ShopAddress shopAddress = new ShopAddress();
			Map<?, ?> result = (Map<?, ?>) results.get(i);
			Map<?, ?> geometry = (Map<?, ?>) result.get("geometry");
			Map<?, ?> location = (Map<?, ?>) geometry.get("location");
			shopDetail.setShopLatitude((Double) location.get("lat"));
			shopDetail.setShopLongitude((Double) location.get("lng"));
			shopDetail.setShopName(shope.getShopName());
			shopAddress.setNumber(shope.getShopAddress().getNumber());
			shopAddress.setPostCode(shope.getShopAddress().getPostCode());
			shopDetail.setShopAddress(shopAddress);
			shopDetails.add(shopDetail);
		}
		return nearestShopLocation(shopDetails,latitude,longitude);
	}

	private ShopDetail nearestShopLocation(List<ShopDetail> shopDetails  ,Double latitude,Double longitude) {
		//Logic for nearest shop need to implement
		return shopDetails.get(0);
	}
}
