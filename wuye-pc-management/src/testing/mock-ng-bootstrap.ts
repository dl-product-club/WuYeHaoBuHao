

import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/of';
import {Response, ResponseOptions} from "@angular/http";

export class HttpMock {
    post() {
        return Observable.of(new Response(new ResponseOptions({body: {}})));
    }

    put() {
        return Observable.of(new Response(new ResponseOptions({body: {}})));
    }

    get() {
        return Observable.of(new Response(new ResponseOptions({body: {}})));
    }

    delete() {
        return Observable.of(new Response(new ResponseOptions({body: {}})));
    }
}

export class CookieServiceMock {
    get(key: string) {
        return key;
    }
    put(key: string, value: string) {
        return { key: key, value: value };
    }
}
