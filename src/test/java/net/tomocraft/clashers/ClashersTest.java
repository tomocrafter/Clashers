package net.tomocraft.clashers;

import net.tomocraft.clashers.conf.ConfigurationBuilder;
import net.tomocraft.clashers.http.ClanSearchParametersBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClashersTest {

	private static final String TOKEN = "";

	Clashers clashers;

	@Test
	public void testSearchClans() throws Exception {
		List<Clan> clans = clashers.searchClans(new ClanSearchParametersBuilder().setName("Light"));
		clans.forEach(clan -> System.out.println(clan.getName()));
		assertTrue(true);
	}

	@BeforeEach
	protected void beforeEach() {
		clashers = new Clashers(new ConfigurationBuilder()
				.setToken(TOKEN)
				.build());
	}
}
