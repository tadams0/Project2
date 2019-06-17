import { UserInfo } from 'src/app/shared/models/userinfo';

export class CreateAccountPayload{
    public type: String;
    public userInfo: UserInfo;
}