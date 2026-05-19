package com.api.Pojo;

import java.util.Arrays;
import java.util.List;

public record CreateJobPayload (// customer .pojo class we have created different pojo as there is nested json
		// Customer became reeferb=nce variable as it's having josn

	 int mst_service_location_id,
	 int mst_platform_id,
	 int mst_warrenty_status_id, 
	 int mst_oem_id,
	 Customer1 customer, 
	 CustomerAddress customer_address,
	 CustomerProduct customer_product,
	 /*Problems problems[])*///problems is json array it's fixed if we want some change need more we can use list
	 List<Problems> problems)
{
	
	
}
