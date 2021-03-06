package com.apisecurity;

import com.apisecurity.auth.HMACSignatureBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HMACSignatureBuilderTest {

    @Test
    public void shouldCreateHMACSignatureForCurrentDateAndStartDateInQuery() {
        String secretKeyValue = "LbjCtUKGl/lGq92KzHfez5FIQMvtQjG9gyZ/ZTqIu8KGeGpJ3xq7zrUCa5MkO7FG83tc/ReNQ1mmNHx+Lhdzp0zl4sBIjYw2QBieIO/kLzGF/khJC6CcsIT74a03m7ML2ml3Rk6YgTaFZr3ScfYH7/p5Wqylzbec4pT5096NflvT+u6uZRNGGgdg/3ClE+uko/fAtNNz/eBgvbfOogPcEbPvMmnDH+d/VlVZldqk8UZV8QmC/3zh6DHZdFfg6JqAAnfpWzJJrez+y2Ns+Jw3devc3jgD6bnxXS1AjqrbIz/zfiicuHYQNuzwtF9BnR2S0n4buwYnIFDFxaZ/hUophPNkJFyLmm5d5JIoExBpPcvLTHYtn39lU71nfqGa3XyHKsFci8JANZe3x9CFIy3QvMvh9Fgoc+YjJ4T6Si21NzQSxCvYbnCETT/AU9CL5od9SBVtP/1o/dutw2QU7YE4SZUvKmy0yVRE/l0oJcGDpq819SFbPUEhJPSKp2wQtTL+n9WL8Nko6lb67HWSesTWml2VWtoAZhd2HC6WzrpHVd/pVvnUriyxu5Yw2jaIt5Z13Ge9SOq/a0kkf3/6q4Cxy4TjkpD8t681MIYs5IY/2vfVCX4sgsbxgde4fQdq7I1MQLw4AQcirnD3riJzQxzgKmJPU5r6PuobVE1vkiSm/W0DEw0m3nkkyzG0a4PJciIanC4MQqEUKOxff71Y5LnNN5pUXC7mR9sdE49Ps5ilzA36HdN568VVQYwRw0nwoCkkViHpy/Kybf9d/6H3ZcXr8qD85JOtXKJGIcp4T1ljHShw2YVZU/wNu/3nvFIij6oZI7I+ZjZ5ccIZlH51MAzzB2YisVl0lG0uoV6NVrooULHvps0c05t05nMnTHbuOWJkrLOrEwQmvCd5pYpMFuYdt/aal7K/GipilPcgUcpFeutHWESemIE1NfBDKJF5dX2rnmwnsqLdkwaO43LKzVpbk7MeufprSFEoaSUwtTo7r+nkwlcQG8NmjmdCVv4pP6P3MM9DI48tBSYiHWNgy5xNTBl1F0zF2ArhCh/GV6xp8C4CKLFEDu/tzEYWTP6zVFLpcORr3D5h8KW40exYtLkkeMcpkqwDhLlHWqRT7RasCllv1aWzvbHs0wu9J5Rn+52Y012AbpnKJLkVnaqyAxcI4XpJ/B2tiiRkHmoG8KiRKqHKYc7HZoJ0VyFyAwwRxk9BXXZ5kXmW/7pL5mwsp0popp1noaV/Kfx5FpeqSqhq5GkkguN+JK8EDL7W8ps2FeciR8YcT6eyUPvUPqhCGbJT+EdbxQll4zkizov7fkliR9A/oY0SJkQFeJ2YHCAozpnzrflr77rITzd34P4mugadow==";
        HMACSignatureBuilder signatureBuilder = new HMACSignatureBuilder(secretKeyValue);

        String signature = signatureBuilder.createSignature("2012-12-202013-02-04");
        assertEquals("cETwkdkwAsHOZ+fbij4OQMTzEW4=", signature);
    }
}
