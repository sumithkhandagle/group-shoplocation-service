

import java.net.URISyntaxException;

import junit.framework.Assert;
import models.ShopAddress;
import models.ShopDetail;

import org.junit.Test;

import controller.GeocodingController;





public class GeocodingControllerTest {
	
	@Test
	public void getGeocodingTest(){
		
		GeocodingController geocodingController= new GeocodingController();
		ShopDetail nearestShopDetails=null;
		Double latitude=22.322281;
		Double longitude=73.165462;
		ShopDetail shopDetail = new ShopDetail();
		shopDetail.setShopName("Inorbit");
		ShopAddress shopAddress= new ShopAddress();
		shopAddress.setNumber(143);
		shopAddress.setPostCode(411014);
		shopDetail.setShopAddress(shopAddress);
		try {
			nearestShopDetails =geocodingController.getGeocoding(shopDetail, latitude, longitude);
		} catch (URISyntaxException e) {
			// exception details
			e.printStackTrace();
		}
		Assert.assertNotNull(nearestShopDetails);
		
	}

}
