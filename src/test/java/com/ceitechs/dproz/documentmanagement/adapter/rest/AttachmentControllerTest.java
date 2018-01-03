/**
 * 
 */
package com.ceitechs.dproz.documentmanagement.adapter.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ceitechs.dproz.documentmanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.documentmanagement.domain.Attachment;
import com.ceitechs.dproz.documentmanagement.repositories.AttachmentRepositoryTest;

/**
 * @author vowino
 *
 */
@WebMvcTest(AttachmentController.class)
public class AttachmentControllerTest extends AbstractDprozServiceIntegrationTest{
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AttachmentController attachmentController;
	
	private static final String PARENT_REFERENCE_ID = "12345678";
	private static final String USER_TOKEN_VALUE = "user@dproz.com:username";
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";
	
	@Test
	public void attachmentUploadsTest() throws Exception {
		MockMultipartFile multiPartFile = new MockMultipartFile("attachment", resource.getFilename(), 
				MediaType.MULTIPART_FORM_DATA_VALUE, resource.getInputStream());

		mvc.perform(MockMvcRequestBuilders.multipart("/api/dproz/attachments/" + PARENT_REFERENCE_ID)
				.file(multiPartFile)
				.with(user(USER).password(PASSWORD))
				.header("user-token", USER_TOKEN_VALUE)
				)
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void deleteAttachmentTest() throws Exception {
		Attachment attachment = AttachmentRepositoryTest.createAttachment();
		ResponseEntity responseObject = new ResponseEntity<>(attachment,HttpStatus.OK);
		
		BDDMockito.given(attachmentController.deleteAttachment(USER_TOKEN_VALUE, PARENT_REFERENCE_ID))
		.willReturn(responseObject);
		
		mvc.perform(delete("/api/dproz/attachments/" + PARENT_REFERENCE_ID)
				.with(user(USER).password(PASSWORD))
				.header("user-token", USER_TOKEN_VALUE)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.referenceId", is(attachment.getReferenceId())));
		
	}
	
}
