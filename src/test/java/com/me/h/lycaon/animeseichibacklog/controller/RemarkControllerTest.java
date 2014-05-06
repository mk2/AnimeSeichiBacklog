package com.me.h.lycaon.animeseichibacklog.controller;

import com.google.common.collect.Lists;
import com.me.h.lycaon.animeseichibacklog.domain.Remark;
import com.me.h.lycaon.animeseichibacklog.domain.RemarkBuilder;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by lycaon_h on 2014/03/22.
 */
public class RemarkControllerTest extends AbstractControllerTest {

    @Test
    public void testCreateRemark() throws Exception {

    }

    @Test
    public void testReadByFeatureId() throws Exception {
        mockMvc.perform(get("/r/xr/0/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
               .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testDeleteRemark() throws Exception {

    }

    @Override
    protected void setupBeans() {
        List<Remark> remarks = Lists.newArrayList(new RemarkBuilder().createRemark());
        when(remarkCrudService.readByFeatureId(any(Long.class))).thenReturn(remarks);
    }
}
