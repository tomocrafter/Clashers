package net.tomocraft.clashers;

import io.github.cdimascio.dotenv.Dotenv;
import net.tomocraft.clashers.conf.ConfigurationBuilder;
import net.tomocraft.clashers.http.ClanSearchParametersBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClashersTest {

	private static final String TOKEN = Dotenv.load().get("TOKEN");

	private Clashers clashers;

	@Test
	public void testSearchClans() throws Exception {
		List<Clan> clans = clashers.searchClans(new ClanSearchParametersBuilder().setName("Light"));

		Clan clan = clans.get(0);
		System.out.println(clan.getJson());
		assertTrue(true);
	}

	@BeforeEach
	protected void beforeEach() {
		clashers = new Clashers(new ConfigurationBuilder()
				.setToken(TOKEN)
				.build());
	}
}
