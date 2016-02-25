package com.apisecurity.auth.validators;

import com.apisecurity.auth.ClientKey;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class HMACSignatureValidatorTest {

    @Test
    public void shouldAuthenticatePartnerForGivenKeyId() {
        HMACSignatureValidator filter = new HMACSignatureValidator();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        ClientKey clientKey = ClientKey.valueOf("soUSFir43BKNh9lrA5vQmQ==",
                "LbjCtUKGl/lGq92KzHfez5FIQMvtQjG9gyZ/ZTqIu8KGeGpJ3xq7zrUCa5MkO7FG83tc/ReNQ1mmNHx+Lhdzp0zl4sBIjYw2QBieIO/kLzGF/khJC6CcsIT74a03m7ML2ml3Rk6YgTaFZr3ScfYH7/p5Wqylzbec4pT5096NflvT+u6uZRNGGgdg/3ClE+uko/fAtNNz/eBgvbfOogPcEbPvMmnDH+d/VlVZldqk8UZV8QmC/3zh6DHZdFfg6JqAAnfpWzJJrez+y2Ns+Jw3devc3jgD6bnxXS1AjqrbIz/zfiicuHYQNuzwtF9BnR2S0n4buwYnIFDFxaZ/hUophPNkJFyLmm5d5JIoExBpPcvLTHYtn39lU71nfqGa3XyHKsFci8JANZe3x9CFIy3QvMvh9Fgoc+YjJ4T6Si21NzQSxCvYbnCETT/AU9CL5od9SBVtP/1o/dutw2QU7YE4SZUvKmy0yVRE/l0oJcGDpq819SFbPUEhJPSKp2wQtTL+n9WL8Nko6lb67HWSesTWml2VWtoAZhd2HC6WzrpHVd/pVvnUriyxu5Yw2jaIt5Z13Ge9SOq/a0kkf3/6q4Cxy4TjkpD8t681MIYs5IY/2vfVCX4sgsbxgde4fQdq7I1MQLw4AQcirnD3riJzQxzgKmJPU5r6PuobVE1vkiSm/W0DEw0m3nkkyzG0a4PJciIanC4MQqEUKOxff71Y5LnNN5pUXC7mR9sdE49Ps5ilzA36HdN568VVQYwRw0nwoCkkViHpy/Kybf9d/6H3ZcXr8qD85JOtXKJGIcp4T1ljHShw2YVZU/wNu/3nvFIij6oZI7I+ZjZ5ccIZlH51MAzzB2YisVl0lG0uoV6NVrooULHvps0c05t05nMnTHbuOWJkrLOrEwQmvCd5pYpMFuYdt/aal7K/GipilPcgUcpFeutHWESemIE1NfBDKJF5dX2rnmwnsqLdkwaO43LKzVpbk7MeufprSFEoaSUwtTo7r+nkwlcQG8NmjmdCVv4pP6P3MM9DI48tBSYiHWNgy5xNTBl1F0zF2ArhCh/GV6xp8C4CKLFEDu/tzEYWTP6zVFLpcORr3D5h8KW40exYtLkkeMcpkqwDhLlHWqRT7RasCllv1aWzvbHs0wu9J5Rn+52Y012AbpnKJLkVnaqyAxcI4XpJ/B2tiiRkHmoG8KiRKqHKYc7HZoJ0VyFyAwwRxk9BXXZ5kXmW/7pL5mwsp0popp1noaV/Kfx5FpeqSqhq5GkkguN+JK8EDL7W8ps2FeciR8YcT6eyUPvUPqhCGbJT+EdbxQll4zkizov7fkliR9A/oY0SJkQFeJ2YHCAozpnzrflr77rITzd34P4mugadow==");

        Map<String, String[]> queryMap = new HashMap<String,String[]>();
        queryMap.put("start-date", new String[] {"2012-12-12"});
        queryMap.put("end-date", new String[] {"2013-01-01"});
        queryMap.put("silo", new String[] {"UKHotels"});
        queryMap.put("bucket", new String[] {"Bucket2"});
        queryMap.put("group_by", new String[] {"silo,bucket,location_id"});

        when(request.getParameterMap()).thenReturn(queryMap);
        when(request.getHeader("Date")).thenReturn("Mon, 01 Jan -4712 00:00:00 +0000");

        String signature = "sk+X3kVEWoTRxgMgj+U00NmnJ6w=";
        assertTrue(filter.isValid(clientKey, signature, request));
    }

    @Test
    public void shouldValidateInvalidSignatures() {
        HMACSignatureValidator filter = new HMACSignatureValidator();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        ClientKey clientKey = ClientKey.valueOf("soUSFir43BKNh9lrA5vQmQ==",
                                                "LbjCtUKGl/lGq92KzHfez5FIQMvtQjG9gyZ/ZTqIu8KGeGpJ3xq7zrUCa5MkO7FG83tc/ReNQ1mmNHx+Lhdzp0zl4sBIjYw2QBieIO/kLzGF/khJC6CcsIT74a03m7ML2ml3Rk6YgTaFZr3ScfYH7/p5Wqylzbec4pT5096NflvT+u6uZRNGGgdg/3ClE+uko/fAtNNz/eBgvbfOogPcEbPvMmnDH+d/VlVZldqk8UZV8QmC/3zh6DHZdFfg6JqAAnfpWzJJrez+y2Ns+Jw3devc3jgD6bnxXS1AjqrbIz/zfiicuHYQNuzwtF9BnR2S0n4buwYnIFDFxaZ/hUophPNkJFyLmm5d5JIoExBpPcvLTHYtn39lU71nfqGa3XyHKsFci8JANZe3x9CFIy3QvMvh9Fgoc+YjJ4T6Si21NzQSxCvYbnCETT/AU9CL5od9SBVtP/1o/dutw2QU7YE4SZUvKmy0yVRE/l0oJcGDpq819SFbPUEhJPSKp2wQtTL+n9WL8Nko6lb67HWSesTWml2VWtoAZhd2HC6WzrpHVd/pVvnUriyxu5Yw2jaIt5Z13Ge9SOq/a0kkf3/6q4Cxy4TjkpD8t681MIYs5IY/2vfVCX4sgsbxgde4fQdq7I1MQLw4AQcirnD3riJzQxzgKmJPU5r6PuobVE1vkiSm/W0DEw0m3nkkyzG0a4PJciIanC4MQqEUKOxff71Y5LnNN5pUXC7mR9sdE49Ps5ilzA36HdN568VVQYwRw0nwoCkkViHpy/Kybf9d/6H3ZcXr8qD85JOtXKJGIcp4T1ljHShw2YVZU/wNu/3nvFIij6oZI7I+ZjZ5ccIZlH51MAzzB2YisVl0lG0uoV6NVrooULHvps0c05t05nMnTHbuOWJkrLOrEwQmvCd5pYpMFuYdt/aal7K/GipilPcgUcpFeutHWESemIE1NfBDKJF5dX2rnmwnsqLdkwaO43LKzVpbk7MeufprSFEoaSUwtTo7r+nkwlcQG8NmjmdCVv4pP6P3MM9DI48tBSYiHWNgy5xNTBl1F0zF2ArhCh/GV6xp8C4CKLFEDu/tzEYWTP6zVFLpcORr3D5h8KW40exYtLkkeMcpkqwDhLlHWqRT7RasCllv1aWzvbHs0wu9J5Rn+52Y012AbpnKJLkVnaqyAxcI4XpJ/B2tiiRkHmoG8KiRKqHKYc7HZoJ0VyFyAwwRxk9BXXZ5kXmW/7pL5mwsp0popp1noaV/Kfx5FpeqSqhq5GkkguN+JK8EDL7W8ps2FeciR8YcT6eyUPvUPqhCGbJT+EdbxQll4zkizov7fkliR9A/oY0SJkQFeJ2YHCAozpnzrflr77rITzd34P4mugadow==");

        Map<String, String[]> queryMap = new HashMap<String,String[]>();
        queryMap.put("start-date", new String[] {"2012-12-12"});
        queryMap.put("end-date", new String[] {"2013-01-01"});
        queryMap.put("silo", new String[] {"UKHotels"});
        queryMap.put("bucket", new String[] {"Bucket2"});
        queryMap.put("group_by", new String[] {"silo,bucket,location_id"});

        when(request.getParameterMap()).thenReturn(queryMap);
        //Mon, 01 Jan -4712 00:00:00 +0000 end-date=2013-01-01&start-date=2012-12-12&bucket=Bucket2&group_by=silo,bucket,location_id&silo=UKHotels
        when(request.getHeader("Date")).thenReturn("Mon, 01 Jan -4712 00:00:00 +0000");

        String signature = "sk%2BX3kVEWoTRxgMgj%2BU00NmnJ6w%3D%0A";
        assertFalse(filter.isValid(clientKey, signature, request));
    }
}
