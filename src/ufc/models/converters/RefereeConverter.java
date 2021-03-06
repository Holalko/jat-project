package ufc.models.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ufc.models.Referee;
import ufc.sessionBean.RefereeSB;

@FacesConverter(forClass=Referee.class)
public class RefereeConverter implements Converter {



		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
			
			if("null".equals(arg2)) {
				return null;
			}
			RefereeSB refereeSB;
			try {
				refereeSB = InitialContext.doLookup("java:global/UltimateFightClub/RefereeSB");
				return refereeSB.find(Long.parseLong(arg2));
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
			if(arg2 == null) {
				return "null";
			}
			return Long.toString(((Referee)arg2).getId());
		}

}
