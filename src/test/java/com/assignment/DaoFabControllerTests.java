package com.assignment;

import com.assignment.dto.response.ChildDetailData;
import com.assignment.dto.response.ParentAmount;
import com.assignment.service.IDaoFabChildTransService;
import com.assignment.service.IDaoFabService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Sarwar Siddique
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DaoFabControllerTests {

	@MockBean
	private IDaoFabService iDaoFabService;
	@MockBean
	private IDaoFabChildTransService iDaoFabChildTransService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getParentTransactionsTest() throws Exception {
		var parentTransactions = new ArrayList<ParentAmount>();

		var parentAmount = new ParentAmount();
		parentAmount.setId(1);
		parentAmount.setSender("ABC");
		parentAmount.setReceiver("XYZ");
		parentAmount.setTotalAmount(200);
		parentAmount.setTotalPaidAmount(100);

		parentTransactions.add(parentAmount);

		when(iDaoFabService.fetchAllParentAmount(anyInt(), anyInt())).thenReturn(parentTransactions);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/internal/v1/daofab-parent-transaction/data?page_no=1"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.[0].id").value(parentAmount.getId()))
			   .andExpect(jsonPath("$.[0].sender").value(parentAmount.getSender()))
			   .andExpect(jsonPath("$.[0].receiver").value(parentAmount.getReceiver()))
			   .andExpect(jsonPath("$.[0].totalAmount").value(parentAmount.getTotalAmount()))
			   .andExpect(jsonPath("$.[0].totalPaidAmount").value(parentAmount.getTotalPaidAmount()));
	}

	@Test
	void getChildTransactionsTest() throws Exception {
		long parentId = 1;

		var childTransactions = new ArrayList<ChildDetailData>();

		var childDetailData = new ChildDetailData();
		childDetailData.setId(1);
		childDetailData.setSender("ABC");
		childDetailData.setReceiver("XYZ");
		childDetailData.setTotalAmount(200);
		childDetailData.setPaidAmount(10);

		childTransactions.add(childDetailData);

		when(iDaoFabChildTransService.fetchChildTransDataByParentId(anyInt())).thenReturn(childTransactions);

		mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/internal/v1/daofab-child-transaction/data?parent_id=1")))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.[0].id").value(childDetailData.getId()))
			   .andExpect(jsonPath("$.[0].sender").value(childDetailData.getSender()))
			   .andExpect(jsonPath("$.[0].receiver").value(childDetailData.getReceiver()))
			   .andExpect(jsonPath("$.[0].totalAmount").value(childDetailData.getTotalAmount()))
			   .andExpect(jsonPath("$.[0].paidAmount").value(childDetailData.getPaidAmount()));
	}

}
