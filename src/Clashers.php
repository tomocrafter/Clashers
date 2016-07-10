<?php

// Main Class

final class Clashers{

	public $token = '';

	public function __construct(string $token){
		$this->token = $token;
	}

	public function getClans(array $parameter, string $mode = null, string $tag = null) : array{
		if($mode === null){
			$url = '';
		}

		switch($mode){
			case 'tag':
				if($id === null){
					throw new ClashersException('Not fount tag', 0);
				}
				$url = '/' . $tag;
				break;

			case 'mem':
				if($id === null){
					throw new ClashersException('Not fount tag', 0);
				}
				$url = '/' . $tag . '/members';
				break;

			default:
				$url = '';
				break;
		}

		return $this->cURL('https://api.clashofclans.com/v1/clans' . $url, $parameter);
	}

	public function getLocations(array $parameter, string $mode = null, $id = null, $rank = null) : array{
		if($mode === null){
			$url = '';
		}

		switch($mode){
			case 'id':
				if($id === null){
					throw new ClashersException('Not fount id', 0);
				}
				$url = '/' . $id;
				break;

			case 'rank':
				if($id === null){
					throw new ClashersException('Not fount id', 0);
				}
				if($id === null){
					throw new ClashersException('Not fount rank', 0);
				}
				$url = '/' . $id . '/rankings/' . $rank;
				break;

			default:
				$url = '';
				break;
		}

		return $this->cURL('https://api.clashofclans.com/v1/locations' . $url, $parameter);
	}

	public function getLeagues(){
		return $this->cURL('https://api.clashofclans.com/v1/leagues');
	}

	public function cURL(string $requestURL, array $parameter = []) : array{
		$headers = [
			'Accept: application/json',
			'authorization: Bearer' . $this->token
		];
		$url = $requestURL . '?' . http_build_query($parameter);
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

		$json =  curl_exec($ch);
		curl_close($ch);

		$json = mb_convert_encoding($json, 'UTF-8', 'EUC-JP');
		return json_decode($json, true);
	}
}
